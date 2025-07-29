package trabalhoFinalLp2;
import java.util.*;
import java.io.*;
public class ID_Senhas {
	
	private static final String USERS_FILE = "users.txt";
    private HashMap<String, String> loginInfo;
    private ArrayList<String> admins;

    public ID_Senhas() {
        loginInfo = new HashMap<>();
        admins = new ArrayList<>();
        loadUsersFromFile();
        
        // Se o arquivo estiver vazio, cria usuários padrão
        if (loginInfo.isEmpty()) {
            loginInfo.put("Nicolas", "0303");
            loginInfo.put("Jorel", "garfo");
            admins.add("Nicolas");
            saveUsersToFile();
        }
    }
    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String username = parts[0];
                    String password = parts[1];
                    boolean isAdmin = Boolean.parseBoolean(parts[2]);
                    
                    loginInfo.put(username, password);
                    if (isAdmin) {
                        admins.add(username);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Arquivo de usuários não encontrado. Criando novo...");
        }
    }

    private void saveUsersToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (Map.Entry<String, String> entry : loginInfo.entrySet()) {
                String username = entry.getKey();
                String password = entry.getValue();
                boolean isAdmin = admins.contains(username);
                writer.println(username + ";" + password + ";" + isAdmin);
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }


    public void addUser(String username, String password, boolean isAdmin) {
        loginInfo.put(username, password);
        if (isAdmin) {
            admins.add(username);
        }
        saveUsersToFile();
    }
	
	protected HashMap getLoginInfo(){
		return loginInfo;
	}
	protected ArrayList getAdmins() {
		return admins;
	}
}

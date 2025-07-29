package trabalhoFinalLp2;
import java.io.*;
public class User {
	private String nome;
	private int senha;
	private Boolean isAdmin;
	public User(String nome,int senha,Boolean isAdmin) {
		this.nome =nome;
		this.senha=senha;
		this.isAdmin=isAdmin;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	public String getNome() {
		return this.nome;
	}
	public void setSenha(int senha) {
		try {
			this.senha=senha;
		}catch(java.util.InputMismatchException e) {
			
		}
	}
}

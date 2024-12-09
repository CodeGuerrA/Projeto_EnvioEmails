package entities;

import java.io.Serializable;

public class Usuarios implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer idade;
	private String email;
	private String number;
	
	public Usuarios() {
	}
	public Usuarios(String name, Integer idade, String email, String number) {
		super();
		this.name = name;
		this.idade = idade;
		this.email = email;
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	

}

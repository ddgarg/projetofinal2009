package br.com.buyFast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Classe que representa o funcionário do sistema.
 * @see Person
 */
@Entity
public class Employee extends Person implements Serializable {
 
	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * O userName do funcionário.<br>
	 * Usado para fazer login na área administrativa do sistema.
	 */
	@Column(name="user_login", nullable=false, length=100, unique=true)
	protected String user;

	/**
	 * Obter o userName do funcionário.
	 * @return o userName do funcionário.
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Ajustar o userName do Funcionário.
	 * @param user - O novo userName do funcionário.
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
}
 

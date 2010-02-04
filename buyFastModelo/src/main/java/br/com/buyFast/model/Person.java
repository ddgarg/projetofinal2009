package br.com.buyFast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Classe que representa as pessoas usuárias do sistema.
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="type")
public abstract class Person implements Serializable {
 
	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O identificador.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long id;
	
	/**
	 * O nome.
	 */
	@Column(nullable=false, length=100)
	protected String name;
	
	/**
	 * O sobrenome.
	 */
	@Column(nullable=false, length=100)
	protected String secondName;
	
	/**
	 * O cadastro de pessoa física.
	 */
	@Column(nullable=true, length=14)
	protected String cpf;

	/**
	 * O e-mail.
	 */
	@Column(nullable=false, length=100)
	protected String email;
	
	/**
	 * A senha.
	 */
	@Column(nullable=false, length=50)
	protected String password;
	
	/**
	 * Obter o identificador.
	 * @return O identificador.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Ajusta o identificador.
	 * @param id - O novo identificador.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Obter o nome.
	 * @return O nome.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Ajustar o nome.
	 * @param name - O novo nome.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obter o segundo nome.
	 * @return O segundo nome.
	 */
	public String getSecondName() {
		return secondName;
	}

	/**
	 * Ajustar o segundo nome.
	 * @param secondName - O segundo nome.
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	/**
	 * Obter o cadastro de pessoa física.
	 * @return O cadastro de pessoa física.
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Ajustar o cadastro de pessoa física.
	 * @param cpf - o novo cadastro de pessoa física.
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Obter o e-mail.
	 * @return O e-mail.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Ajustar o e-mail.
	 * @param email - O novo e-mail.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obter a senha.
	 * @return A senha.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Ajustar a senha.
	 * @param password - A nova senha.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
 

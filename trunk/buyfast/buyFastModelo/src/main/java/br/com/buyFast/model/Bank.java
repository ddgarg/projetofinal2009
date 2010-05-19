package br.com.buyFast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe que representa o banco com o qual o sistema trabalha.
 */
@Entity
public class Bank implements Serializable {
 
	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O identificador do banco.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * O nome do banco.
	 */
	@Column(nullable=false, length=100)
	private String name;

	/**
	 * Construtor da classe banco.
	 */
	public Bank() {
	}
	
	/**
	 * Construtor da classe banco.<br>
	 * Criará uma instância de banco com o nome configurado.
	 * @param name - O nome do banco.
	 */
	public Bank(String name) {
		this.name = name;
	}
	
	/**
	 * Obter o identificador do banco.
	 * @return O identificador do banco.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Ajustar o identificador do banco.
	 * @param id - O novo identificador do banco.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Obter o nome do banco.
	 * @return O nome do banco.
	 */
	public String getBank() {
		return name;
	}

	/**
	 * Ajustar o nome do banco.
	 * @param bank - O novo nome do banco.
	 */
	public void setBank(String bank) {
		this.name = bank;
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
		Bank other = (Bank) obj;
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
		return this.name;
	}
	
}
 

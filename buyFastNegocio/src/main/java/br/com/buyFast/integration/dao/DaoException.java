package br.com.buyFast.integration.dao;

import java.io.Serializable;

/**
 * Classe responsável pelos lançamentos das exceções na camada DAO.
 */
public class DaoException extends Exception {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instancia um novo {@link DaoException}.
	 */
	public DaoException() {
		super();
	}
	
	/**
	 * Instancia um novo {@link DaoException}.
	 * @param arg0 a mensagem que será apresentada na exceção.
	 */
	public DaoException(String arg0) {
		super(arg0);
	}
	
	/**
	 * Instancia um novo {@link DaoException}.
	 * @param arg0 erros e exceções da linguagem java.
	 */
	public DaoException(Throwable arg0) {
		super(arg0);
	}
	
	/**
	 * Instancia um novo {@link DaoException}.
	 * @param arg0 a mensagem que será apresentada na exceção.
	 * @param arg1 erros e exceções da linguagem java.
	 */
	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}

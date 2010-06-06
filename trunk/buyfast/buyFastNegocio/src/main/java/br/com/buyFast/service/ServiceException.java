package br.com.buyFast.service;

import java.io.Serializable;

/**
 * Classe responsável pelos lançamentos das exceções na camada de serviço.
 * @see Exception
 */
public class ServiceException extends Exception {
	
	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instancia um novo {@link ServiceException}.
	 */
	public ServiceException() {
		super();
	}
	
	/**
	 * Instancia um novo {@link ServiceException}.
	 * @param arg0 a mensagem que será apresentada na exceção.
	 */
	public ServiceException(String arg0) {
		super(arg0);
	}
	
	/**
	 * Instancia um novo {@link ServiceException}.
	 * @param arg0 erros e exceções da linguagem java.
	 */
	public ServiceException(Throwable arg0) {
		super(arg0);
	}
	
	/**
	 * Instancia um novo {@link ServiceException}.
	 * @param arg0 a mensagem que será apresentada na exceção.
	 * @param arg1 erros e exceções da linguagem java.
	 */
	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}

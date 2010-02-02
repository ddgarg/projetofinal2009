package br.com.buyFast.integration;

import java.io.Serializable;

/**
 * Classe responsável 
 */
public class IntegrationException extends Exception {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instancia um novo {@link IntegrationException}.
	 */
	public IntegrationException() {
		super();
	}
	
	/**
	 * Instancia um novo {@link IntegrationException}.
	 * @param arg0 a mensagem que será apresentada na exceção.
	 */
	public IntegrationException(String arg0) {
		super(arg0);
	}
	
	/**
	 * Instancia um novo {@link IntegrationException}.
	 * @param arg0 erros e exceções da linguagem java.
	 */
	public IntegrationException(Throwable arg0) {
		super(arg0);
	}
	
	/**
	 * Instancia um novo {@link IntegrationException}.
	 * @param arg0 a mensagem que será apresentada na exceção.
	 * @param arg1 erros e exceções da linguagem java.
	 */
	public IntegrationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}

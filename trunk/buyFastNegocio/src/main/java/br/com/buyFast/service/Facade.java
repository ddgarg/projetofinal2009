package br.com.buyFast.service;

import br.com.buyFast.model.Administrator;

/**
 * Classe que representa a fachada para a camada de serviço da aplicação.
 */
public interface Facade {

	/**
	 * Verifica e retorna o administrador através do login e senha do usuário.
	 * @param userLogin O login do administrador. 
	 * @return O objeto {@link Administrator} dono do login.
	 * @throws ServiceException 
	 */
	Administrator checkAdministrator(String userLogin, String password) throws ServiceException;
}

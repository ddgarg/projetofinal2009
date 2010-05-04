package br.com.buyFast.integration.dao;

import br.com.buyFast.model.Administrator;

/**
 * Classe que representa o objeto de acesso a dados para o modelo {@link Administrator}.
 */
public interface AdminDao extends GenericDao<Administrator, Integer> {

	/**
	 * Obter o administrador através do usuário e senha.
	 * @param user o usuário.
	 * @param password a senha.
	 * @return o administrador através do usuário e senha. 
	 * @throws DaoException
	 */
	Administrator getLoginAndPassword(String user, String password)
			throws DaoException;

}

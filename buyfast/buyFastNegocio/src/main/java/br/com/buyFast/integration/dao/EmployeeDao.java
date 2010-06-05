package br.com.buyFast.integration.dao;

import br.com.buyFast.model.Employee;

/**
 * Classe que representa o objeto de acesso a dados para o modelo {@link Employee}.
 */
public interface EmployeeDao extends GenericDao<Employee, Integer> {

	/**
	 * Obter o funcionário através do usuário e senha.
	 * @param user o usuário.
	 * @param password a senha.
	 * @return o funcionário através do usuário e senha. 
	 * @throws DaoException
	 */
	Employee getLoginAndPassword(String user, String password)
			throws DaoException;
}

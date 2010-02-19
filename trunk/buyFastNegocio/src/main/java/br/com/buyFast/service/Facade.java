package br.com.buyFast.service;

import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Employee;
import br.com.buyFast.model.Person;

/**
 * Classe que representa a fachada para a camada de serviço da aplicação.
 */
public interface Facade {

	/**
	 * Verifica e retorna o administrador através do login e senha do usuário.
	 * @param userLogin O login do administrador. 
	 * @return O objeto {@link Administrator} dono do login.
	 * @see Employee
	 * @throws ServiceException 
	 */
	Administrator checkAdministrator(String userLogin, String password) throws ServiceException;
	
	/**
	 * Verifica e retorna o funcionário através do login e senha do usuário.
	 * @param userLogin O login do funcionário. 
	 * @return O objeto {@link Employee} dono do login.
	 * @see Person
	 * @throws ServiceException 
	 */
	Employee checkEmployee (String userLogin, String password) throws ServiceException;
}

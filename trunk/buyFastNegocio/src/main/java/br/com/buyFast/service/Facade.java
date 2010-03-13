package br.com.buyFast.service;

import java.util.List;

import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Category;
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
	Employee checkEmployee(String userLogin, String password) throws ServiceException;
	
	/**
	 * Persiste a categoria no banco de dados.
	 * @see Category
	 * @param category a categoria que será salva no banco de dados.
	 * @throws ServiceException
	 */
	void saveCategory(Category category) throws ServiceException;

	/**
	 * Remove a categoria no banco de dados.
	 * @param category a categoria que será removida do banco de dados.
	 * @throws ServiceException
	 */
	void removeCategory(Category category) throws ServiceException;
	
	/**
	 * Atualiza a categoria no banco de dados.
	 * @param category a categoria que será atualizada.
	 * @throws ServiceException
	 */
	void updateCategory(Category category) throws ServiceException;
	
	/**
	 * Verifica se a categoria já existe no banco de dados.
	 * @param category a categoria que será verificada.
	 * @return <code>true</code> caso já exista a categoria.
	 * <code>false</code> caso contrário.
	 * @throws ServiceException 
	 */
	boolean isAnExistingCategory(Category category) throws ServiceException;

	/**
	 * Obter todas as categorias.
	 * @return um conjunto de categorias.
	 * @throws ServiceException
	 */
	List<Category> getCategories() throws ServiceException;
}

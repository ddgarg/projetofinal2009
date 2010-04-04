package br.com.buyFast.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Category;
import br.com.buyFast.model.Employee;
import br.com.buyFast.model.Person;
import br.com.buyFast.model.Product;

/**
 * Classe que representa a fachada para a camada de serviço da aplicação.
 */
public interface Facade extends Serializable {

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
	
	/**
	 * Salva o arquivo de imagem em disco.
	 * @param buf
	 * @param inputStream
	 * @param fileName nome do arquivo.
	 * @param size tamanho do arquivo.
	 * @param path caminho físico do arquivo.
	 * @return <code>true</code> no sucesso da operação.
	 * <code>false</code> caso contrário.
	 * @throws ServiceException 
	 */
	void fileSave(byte[] buf, InputStream inputStream, String fileName, int size, String path) throws ServiceException;

	/**
	 * Salva o produto no banco de dados.
	 * @param product o produto a ser persistido no banco.
	 * @throws ServiceException
	 */
	void saveProduct(Product product) throws ServiceException;

	/**
	 * Remove o produto no banco de dados.
	 * @param product o produto que será removido.
	 * @throws ServiceException
	 */
	void removeProduct(Product product) throws ServiceException;

	/**
	 * Atualiza o produto no banco de dados.
	 * @param product o produto que será atualizado.
	 * @throws ServiceException
	 */
	void updateProduct(Product product) throws ServiceException;

	/**
	 * Verifica se o produto já existe no banco de dados.
	 * @param product o produto que será verificado.
	 * @return <code>true</code> caso já exista o produto.
	 * <code>false</code> caso contrário.
	 * @throws ServiceException 
	 */
	boolean isAnExistingProduct(Product product) throws ServiceException;

	/**
	 * Obter a categoria através da informação do id.
	 * @param id o id da categoria.
	 * @return a categoria do banco de dados.
	 * @throws ServiceException
	 */
	Category getCategory(int id) throws ServiceException;

	/**
	 * Obter todos os produtos da base de dados.
	 * @return conjunto com todos os produtos da base de dados.
	 * @throws ServiceException
	 */
	List<Product> getAllProducts() throws ServiceException;

	/**
	 * Obter o produto a partir do id informado.
	 * @param id o id do produto.
	 * @return o produto obtido a partir do id.
	 * @throws ServiceException
	 */
	Product getProduct(int id) throws ServiceException;
}

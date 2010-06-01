package br.com.buyFast.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Bank;
import br.com.buyFast.model.Category;
import br.com.buyFast.model.Customer;
import br.com.buyFast.model.Employee;
import br.com.buyFast.model.ItemsOrder;
import br.com.buyFast.model.Order;
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

	/**
	 * Obter os últimos produtos cadastrados na base de dados.
	 * @param maxResult o máximo de resultados.
	 * @return a lista dos últimos produtos cadastrados.
	 * @throws ServiceException
	 */
	List<Product> getLatestProducts(int maxResult) throws ServiceException;

	/**
	 * Obter todos os produtos em promoções.
	 * @return a lista com os produtos em promoção.
	 * @throws ServiceException
	 */
	List<Product> getAllPromotionProducts() throws ServiceException;

	/**
	 * Obter os produtos por categoria.
	 * @param idCategory O código da categoria.
	 * @return A lista com os produtos da categoria.
	 * @throws ServiceException
	 */
	List<Product> getProductsToCategory(int idCategory) throws ServiceException;

	/**
	 * Obter o usuário pelo e-mail.
	 * @param email o e-mail do usuário.
	 * @return o usuário cadastrado na base de dados.
	 * @throws ServiceException
	 */
	Customer getCustomerLogin(String email) throws ServiceException;
	
	/**
	 * Persiste o registro do cliente em banco.
	 * @param customer o cliente que será persistido.
	 * @throws ServiceException
	 */
	void customerRecord(Customer customer) throws ServiceException;
	
	/**
	 * Obter os produtos através da palavra-chave.
	 * @param keyWord a palavra-chave para pesquisa.
	 * @return os produtos relacionados com a palavra-chave.
	 * @throws ServiceException
	 */
	List<Product> productSearch(String keyWord) throws ServiceException;

	/**
	 * Atualiza o usuário na base de dados.
	 * @param customer O modelo cliente que será atualizado.
	 * @throws ServiceException
	 */
	void customerUpdate(Customer customer) throws ServiceException;
	
	/**
	 * Obter todos os bancos da base de dados.
	 * @return O conjunto de bancos da base de dados.
	 * @throws ServiceException
	 */
	List<Bank> getAllBanks() throws ServiceException;
	
	/**
	 * Persiste o banco na base de dados.
	 * @param bank O banco que será persistido.
	 * @throws ServiceException
	 */
	void saveBank(Bank bank) throws ServiceException;
	
	/**
	 * Remove da base de dados o banco.
	 * @param bank O banco que será removido da base de dados.
	 * @throws ServiceException
	 */
	void removeBank(Bank bank) throws ServiceException;
	
	/**
	 * Atualiza o banco na base de dados.
	 * @param bank O banco que será atualizado. 
	 * @throws ServiceException
	 */
	void updateBank(Bank bank) throws ServiceException;
	
	/**
	 * Persiste o pedido na base de dados.
	 * @param order O pedido que será persistido.
	 * @return O pedido atualizado da base de dados.
	 */
	Order saveOrder(Order order) throws ServiceException;
	
	/**
	 * Atualiza o pedido na base de dados.
	 * @param order O pedido que será atualizado.
	 * @return O pedido atualizado.
	 * @throws ServiceException
	 */
	Order updateOrder(Order order) throws ServiceException;
	
	/**
	 * Persiste o item de pedido na base de dados.
	 * @param itemsOrder O item de pedido que será persistido.
	 * @return O item de pedido atualizado da base de dados.
	 * @throws ServiceException
	 */
	ItemsOrder saveItemsOrder(ItemsOrder itemsOrder) throws ServiceException;
	
	/**
	 * Persiste o item de pedido na base de dados.
	 * @param itemsOrder O item de pedido que será persistido.
	 * @return O item de pedido atualizado da base de dados.
	 * @throws ServiceException
	 */
	ItemsOrder mergeItemsOrder(ItemsOrder itemsOrder) throws ServiceException;
	
	/**
	 * Gera o boleto de pagamento.
	 * @param order O pedido para gerar o boleto.
	 * @throws Exception 
	 * @throws ServiceException
	 */
	void generateBoleto(Order order) throws Exception;

	/**
	 * Obter o banco pelo Id.
	 * @param id O identificador do Banco.
	 * @return O banco da base de dados.
	 * @throws ServiceException
	 */
	Bank getBankToId(Integer id) throws ServiceException;
	
	/**
	 * Obter os pedidos não pagos do cliente.
	 * @param customer O cliente ao qual será verificado os pedidos.
	 * @return A lista de pedidos não pagos do cliente.
	 * @throws ServiceException
	 */
	List<Order> getOrdersNotPaid(Customer customer) throws ServiceException;
	
	/**
	 * Obter os pedidos do cliente.
	 * @param customer O cliente ao qual será verificado os pedidos.
	 * @return A lista de pedidos do cliente.
	 * @throws ServiceException
	 */
	List<Order> getOrders(Customer customer) throws ServiceException;
}

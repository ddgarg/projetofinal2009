package br.com.buyFast.service.serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.buyFast.integration.dao.AddressDao;
import br.com.buyFast.integration.dao.AdminDao;
import br.com.buyFast.integration.dao.BankDao;
import br.com.buyFast.integration.dao.CategoryDao;
import br.com.buyFast.integration.dao.CustomerDao;
import br.com.buyFast.integration.dao.DaoException;
import br.com.buyFast.integration.dao.EmployeeDao;
import br.com.buyFast.integration.dao.ItemsOrderDao;
import br.com.buyFast.integration.dao.OrderDao;
import br.com.buyFast.integration.dao.ProductDao;
import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Bank;
import br.com.buyFast.model.Category;
import br.com.buyFast.model.Customer;
import br.com.buyFast.model.Employee;
import br.com.buyFast.model.ItemsOrder;
import br.com.buyFast.model.Order;
import br.com.buyFast.model.Product;
import br.com.buyFast.model.StatusEnum;
import br.com.buyFast.service.BoletoService;
import br.com.buyFast.service.Facade;
import br.com.buyFast.service.ServiceException;

/**
 * Classe que implementa a interface {@link Facade}.
 */
public class FacadeImpl implements Facade {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Apresenta o log na aplicação.
	 */
	private static final Log logger = LogFactory.getLog(FacadeImpl.class);
	
	/**
	 * Objeto de acesso a dados de {@link Administrator}.
	 */
	private AdminDao adminDao;
	
	/**
	 * Objeto de acesso a dados de {@link Employee}.
	 */
	private EmployeeDao employeeDao; 
	
	/**
	 * Objeto de acesso a dados de {@link Category}.
	 */
	private CategoryDao categoryDao;
	
	/**
	 * Objeto de acesso a dados de {@link Product}.
	 */
	private ProductDao productDao;

	/**
	 * Objeto de acesso a dados de {@link Customer}.
	 */
	private CustomerDao customerDao;
	
	/**
	 * Objeto de acesso a dados de {@link AddressDao}.
	 */
	private AddressDao addressDao;
	
	/**
	 * Objeto de acesso a dados de {@link BankDao}.
	 */
	private BankDao bankDao;
	
	/**
	 * Objeto de acesso a dados de {@link OrderDao}.
	 */
	private OrderDao orderDao;
	
	/**
	 * Objeto de acesso a dados de {@link ItemsOrder}.
	 */
	private ItemsOrderDao itemsOrderDao;
	
	/**
	 * Camada de serviço para geração de boleto.
	 */
	private BoletoService boletoService;
	
	/**
	 * Instancia um novo {@link Facade}.
	 * @param adminDao
	 * @param employeeDao
	 * @param categoryDao
	 * @param productDao
	 * @param customerDao
	 * @param addressDao
	 * @param bankDao
	 * @param orderDao
	 * @param itemsOrderDao
	 * @param boletoService
	 */
	public FacadeImpl(AdminDao adminDao, EmployeeDao employeeDao, CategoryDao categoryDao,
			ProductDao productDao, CustomerDao customerDao, AddressDao addressDao,
			BankDao bankDao, OrderDao orderDao, ItemsOrderDao itemsOrderDao,
			BoletoService boletoService) {
		this.adminDao = adminDao;
		this.employeeDao = employeeDao;
		this.categoryDao = categoryDao;
		this.productDao = productDao;
		this.customerDao = customerDao;
		this.addressDao = addressDao;
		this.bankDao = bankDao;
		this.orderDao = orderDao;
		this.itemsOrderDao = itemsOrderDao;
		this.boletoService = boletoService;
	}
	
	@Override
	public Administrator checkAdministrator(String userLogin, String password) throws ServiceException {
		try {
			logger.info("Obtendo administrador no banco de dados ...");
			return adminDao.getLoginAndPassword(userLogin, password);
		} catch (Exception e) {
			String messageError = "Erro ao verificar usuário e senha de administrador.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}
	
	@Override
	public Employee checkEmployee(String userLogin, String password) throws ServiceException {
		try {
			logger.info("Obtendo funcionário no banco de dados ...");
			return employeeDao.getLoginAndPassword(userLogin, password);
		} catch (Exception e) {
			String messageError = "Erro ao verificar usuário e senha de funcionário.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}
	
	@Override
	public void saveCategory(Category category) throws ServiceException {
		try {
			logger.info("Salvando categoria " + category + " no banco de dados ...");
			this.categoryDao.save(category);
		} catch (Exception e) {
			String messageError = "Erro ao salvar categoria " + category + ".";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}
	
	@Override
	public boolean isAnExistingCategory(Category category) throws ServiceException {
		try {
			String query = "SELECT c FROM Category c WHERE c.name = :categoryName";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("categoryName", category.getName());
			logger.info("Verificando se categoria existe no banco de dados ...");
			if (!this.categoryDao.listSearchParam(query, params).isEmpty()) {
				logger.info("Categoria " + category + " já existe.");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			String messageError = "Erro ao verificar existência de categoria.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}
	
	@Override
	public List<Category> getCategories() throws ServiceException {
		try {
			logger.info("Obter todas as categorias ...");
			return categoryDao.all();
		} catch (Exception e) {
			String messageError = "Erro ao obter todas as categorias.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}
	
	public Category getCategory(int id) throws ServiceException {
		try {
			logger.info("Obter categoria de id = " + id + " ...");
			return categoryDao.searchById(id);
		} catch (Exception e) {
			String messageError = "Erro ao obter categoria.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}

	@Override
	public void removeCategory(Category category) throws ServiceException {
		try {
			logger.info("Removendo categoria " + category + " ...");
			categoryDao.delete(category);
		} catch (DaoException e) {
			String messageError = "Erro ao remover categorias.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}

	@Override
	public void updateCategory(Category category) throws ServiceException {
		try {
			logger.info("Atualizando categoria " + category + " ...");
			categoryDao.update(category);
		} catch (DaoException e) {
			String messageError = "Erro ao atualizar categorias.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}
	
	@Override
	public void fileSave(byte[] buf, InputStream inputStream, String fileName, int size, String path) throws ServiceException {
		File file = new File(path + "/" + fileName);
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
		} catch (FileNotFoundException fnfe) {
			String error = "Arquivo não encontrado.";
			logger.error(error, fnfe);
			throw new ServiceException(error, fnfe);
		}
		try {
			while(true) {
				int cont;
				cont = inputStream.read(buf, 0, size);
				if (cont == -1) {
					break;
				}
				fileOutputStream.write(buf, 0, cont);
			}
		} catch (IOException e) {
			String error = "Erro ao gravar arquivo.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		} finally {
			try {
				fileOutputStream.flush();
				fileOutputStream.close();
				inputStream.close();
			} catch (Exception e) {
				String error = "Erro ao fechar stream após salvar arquivo.";
				logger.error(error, e);
				throw new ServiceException(error, e);
			}
		}
	}
	
	public void saveProduct(Product product) throws ServiceException {
		try {
			logger.info("Salvando produto " + product + " no banco de dados ...");
			this.productDao.save(product);
		} catch (Exception e) {
			String messageError = "Erro ao salvar produto " + product + ".";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}
	
	public void removeProduct(Product product) throws ServiceException {
		try {
			logger.info("Removendo produto " + product + " ...");
			productDao.delete(product);
		} catch (DaoException e) {
			String messageError = "Erro ao remover produto.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}
	
	public void updateProduct(Product product) throws ServiceException {
		try {
			logger.info("Atualizando produto " + product + " ...");
			productDao.update(product);
		} catch (DaoException e) {
			String messageError = "Erro ao atualizar produto.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}
	
	public boolean isAnExistingProduct(Product product) throws ServiceException {
		String query = "SELECT p FROM Product p where p.name = :productName";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productName", product.getName());
		try {
			logger.info("Verificando a existência do produto " + product + " ...");
			if (!this.productDao.listSearchParam(query, params).isEmpty()) {
				logger.info("Produto " + product + " já existe.");
				return true;
			} else {
				return false;
			}
		} catch (DaoException e) {
			String error = "Erro ao verificar existência de produto.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}
	
	public List<Product> getAllProducts() throws ServiceException {
		try {
			logger.info("Obtendo todos os produtos...");
			return productDao.all();
		} catch (DaoException e) {
			String error = "Erro ao obter produtos.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}
	
	public List<Product> getLatestProducts(int maxResult) throws ServiceException {
		try {
			logger.info("Obtendo os últimos produtos...");
			String query = "SELECT p FROM Product p ORDER BY id DESC";
			Map<String, Object> params = new HashMap<String, Object>();
			return productDao.listSearchParam(query, params, maxResult, 1);
		} catch (DaoException e) {
			String error = "Erro ao obter produtos.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}
	
	public List<Product> getAllPromotionProducts() throws ServiceException {
		logger.info("Obtendo produtos em promoção...");
		String query = "SELECT p FROM Product p WHERE p.discount > 0 ORDER BY id DESC";
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			return productDao.listSearchParam(query, params);
		} catch (DaoException e) {
			String error = "Erro ao obter produtos.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}
	
	public List<Product> getProductsToCategory(int idCategory) throws ServiceException {
		logger.info("Obtendo produtos por categoria...");
		String query = "SELECT p FROM Product p WHERE p.category.id = :idCategory";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idCategory", idCategory);
		try {
			return productDao.listSearchParam(query, params);
		} catch (DaoException e) {
			String error = "Erro ao obter produtos.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}
	
	public Product getProduct(int id) throws ServiceException {
		try {
			logger.info("Obtendo o produto com id = " + id + " ...");
			return productDao.searchById(id);
		} catch (DaoException e) {
			String error = "Erro ao obter produto.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}
	
	public Customer getCustomerLogin(String email) throws ServiceException {
		logger.info("Obtendo usuário do e-mail " + email + " ...");
		String query = "SELECT c FROM Customer c WHERE c.email = :email";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		try {
			List<Customer> list = customerDao.listSearchParam(query, params);
			if (list != null) {
				if (!list.isEmpty()) {
					return list.get(0);
				}
			}
			
			return null;
		} catch (DaoException e) {
			String error = "Erro ao obter usuário.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}

	@Override
	public void customerRecord(Customer customer) throws ServiceException {
		try {
			logger.info("Salvando cadastro do cliente " + customer + "...");
			addressDao.save(customer.getAddress());
			customerDao.save(customer);
		} catch (DaoException e) {
			String error = "Erro ao salvar cadastro do cliente.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}
	
	@Override
	public List<Product> productSearch(String keyWord) throws ServiceException {
		logger.info("Obtendo produtos pela chave " + keyWord);
		try {
			return this.productDao.productSearch(keyWord);
		} catch (DaoException e) {
			String error = "Erro ao obter lista de produtos para " + keyWord;
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}

	@Override
	public void customerUpdate(Customer customer) throws ServiceException {
		logger.info("Atualizando cliente " + customer);
		try {
			this.customerDao.update(customer);
		} catch (DaoException e) {
			String error = "Erro ao atualizar cliente " + customer;
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}

	public Bank getBankToId(Integer id) throws ServiceException {
		logger.info("Obtendo banco...");
		try {
			return this.bankDao.searchById(id);
		} catch (DaoException e) {
			String error = "Erro ao obter banco.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}
	
	@Override
	public List<Bank> getAllBanks() throws ServiceException {
		logger.info("Obtendo a lista de bancos...");
		try {
			return this.bankDao.all();
		} catch (DaoException e) {
			String error = "Erro ao obter a lista de bancos da base de dados.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}

	@Override
	public void removeBank(Bank bank) throws ServiceException {
		logger.info("Removendo o banco " + bank + " da base de dados.");
		try {
			this.bankDao.delete(bank);
		} catch (DaoException e) {
			String error = "Erro ao remover banco " + bank + " da base de dados.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}

	@Override
	public void saveBank(Bank bank) throws ServiceException {
		logger.info("Salvando banco " + bank + " na base de dados.");
		try {
			this.bankDao.save(bank);
		} catch (DaoException e) {
			String error = "Erro ao persistir banco " + bank + ".";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}

	@Override
	public void updateBank(Bank bank) throws ServiceException {
		logger.info("Atualizando banco " + bank + " na base de dados.");
		try {
			this.bankDao.update(bank);
		} catch (DaoException e) {
			String error = "Erro ao atualizar banco " + bank + ".";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}

	@Override
	public Order saveOrder(Order order) throws ServiceException {
		try {
			logger.info("Salvando pedido " + order + " no banco de dados ...");
			return this.orderDao.save(order);
		} catch (Exception e) {
			String messageError = "Erro ao salvar pedido " + order + ".";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}
	
	@Override
	public ItemsOrder saveItemsOrder(ItemsOrder itemsOrder) throws ServiceException {
		try {
			logger.info("Salvando item de pedido " + itemsOrder + " no banco de dados ...");
			return this.itemsOrderDao.save(itemsOrder);
		} catch (Exception e) {
			String messageError = "Erro ao salvar item de pedido " + itemsOrder + ".";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}
	
	@Override
	public ItemsOrder mergeItemsOrder(ItemsOrder itemsOrder) throws ServiceException {
		try {
			logger.info("Salvando item de pedido " + itemsOrder + " no banco de dados ...");
			return this.itemsOrderDao.merge(itemsOrder);
		} catch (Exception e) {
			String messageError = "Erro ao salvar item de pedido " + itemsOrder + ".";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}

	@Override
	public void generateBoleto(Order order) throws Exception {
		if (order.getBank().getBank() == null) {
			try {
				order.setBank(getBankToId(order.getBank().getId()));
			} catch (ServiceException e) {
				logger.error("Erro ao obter banco para o boleto.", e);
				throw new ServiceException("Erro ao obter banco para o boleto.", e);
			}
		}
		try {
			boletoService.generateBoleto(order);
		} catch (Exception e) {
			logger.error("Erro ao gerar boleto.", e);
			throw new Exception("Erro ao gerar boleto.", e);
		}
	}

	@Override
	public List<Order> getOrdersNotPaid(Customer customer) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customer.getId());
		params.put("status", StatusEnum.CHECKPAYMENT);
		String query = "FROM Order o WHERE o.customer.id = :id AND o.statusEnum = :status";
		try {
			return this.orderDao.listSearchParam(query, params);
		} catch (DaoException e) {
			logger.error("Erro ao obter pedidos não pagos do cliente " + customer.getName(), e);
			throw new ServiceException("Erro ao obter pedidos não pagos do cliente " + customer.getName(), e);
		}
	}

	@Override
	public Order updateOrder(Order order) throws ServiceException {
		try {
			logger.info("Atualiza o pedido " + order + " no banco de dados ...");
			return this.orderDao.update(order);
		} catch (Exception e) {
			String messageError = "Erro ao atualizar pedido " + order + ".";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}

	@Override
	public List<Order> getOrders(Customer customer) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customer.getId());
		params.put("status", StatusEnum.CHECKPAYMENT);
		String query = "FROM Order o WHERE o.customer.id = :id AND NOT o.statusEnum = :status";
		try {
			return this.orderDao.listSearchParam(query, params);
		} catch (DaoException e) {
			logger.error("Erro ao obter pedidos do cliente " + customer.getName(), e);
			throw new ServiceException("Erro ao obter pedidos do cliente " + customer.getName(), e);
		}
	}

	@Override
	public void saveEmployee(Employee employee) throws ServiceException {
		try {
			logger.info("Salvando funcionário " + employee + " no banco de dados ...");
			this.employeeDao.save(employee);
		} catch (Exception e) {
			String messageError = "Erro ao salvar funcionário " + employee + ".";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}

	@Override
	public List<Employee> getAllEmployee() throws ServiceException {
		logger.info("Obtendo a lista de funcionários...");
		try {
			return this.employeeDao.all();
		} catch (DaoException e) {
			String error = "Erro ao obter a lista de funcionários da base de dados.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}

	@Override
	public List<Administrator> getAllAdministrator() throws ServiceException {
		logger.info("Obtendo a lista de administradores...");
		try {
			return this.adminDao.all();
		} catch (DaoException e) {
			String error = "Erro ao obter a lista de administradores da base de dados.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}

	@Override
	public void saveAdmin(Administrator administrator) throws ServiceException {
		try {
			logger.info("Salvando administrador " + administrator + " no banco de dados ...");
			this.adminDao.save(administrator);
		} catch (Exception e) {
			String messageError = "Erro ao salvar administrador " + administrator + ".";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}

	@Override
	public void removeAdmin(Administrator admin) throws ServiceException {
		logger.info("Removendo o administrador " + admin + " da base de dados.");
		try {
			this.adminDao.delete(admin);
		} catch (DaoException e) {
			String error = "Erro ao remover administrador " + admin + " da base de dados.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}

	@Override
	public void removeEmployee(Employee employee) throws ServiceException {
		logger.info("Removendo o funcionário " + employee + " da base de dados.");
		try {
			this.employeeDao.delete(employee);
		} catch (DaoException e) {
			String error = "Erro ao remover funcionário " + employee + " da base de dados.";
			logger.error(error, e);
			throw new ServiceException(error, e);
		}
	}

	@Override
	public void updateAdmin(Administrator admin) throws ServiceException {
		try {
			logger.info("Atualizando administrador " + admin + " ...");
			adminDao.update(admin);
		} catch (DaoException e) {
			String messageError = "Erro ao atualizar administrador.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}

	@Override
	public void updateEmployee(Employee employee) throws ServiceException {
		try {
			logger.info("Atualizando funcionário " + employee + " ...");
			employeeDao.update(employee);
		} catch (DaoException e) {
			String messageError = "Erro ao atualizar funcionário.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}

}

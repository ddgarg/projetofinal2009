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

import br.com.buyFast.integration.dao.AdminDao;
import br.com.buyFast.integration.dao.CategoryDao;
import br.com.buyFast.integration.dao.DaoException;
import br.com.buyFast.integration.dao.EmployeeDao;
import br.com.buyFast.integration.dao.ProductDao;
import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Category;
import br.com.buyFast.model.Employee;
import br.com.buyFast.model.Product;
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
	 * Instancia um novo {@link Facade}.
	 * @param adminDao
	 * @param employeeDao
	 * @param categoryDao
	 * @param productDao
	 */
	public FacadeImpl(AdminDao adminDao, EmployeeDao employeeDao, CategoryDao categoryDao,
			ProductDao productDao) {
		this.adminDao = adminDao;
		this.employeeDao = employeeDao;
		this.categoryDao = categoryDao;
		this.productDao = productDao;
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
			logger.error(messageError);
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
			logger.error(messageError);
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
			logger.error(error);
			throw new ServiceException(error, e);
		}
	}
	
	public Product getProduct(int id) throws ServiceException {
		try {
			logger.info("Obtendo o produto com id = " + id + " ...");
			return productDao.searchById(id);
		} catch (DaoException e) {
			String error = "Erro ao obter produto.";
			logger.error(error);
			throw new ServiceException(error, e);
		}
	}

}

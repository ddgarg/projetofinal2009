package br.com.buyFast.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.buyFast.integration.dao.AdminDao;
import br.com.buyFast.integration.dao.CategoryDao;
import br.com.buyFast.integration.dao.EmployeeDao;
import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Category;
import br.com.buyFast.model.Employee;
import br.com.buyFast.service.Facade;
import br.com.buyFast.service.ServiceException;

/**
 * Classe que implementa a interface {@link Facade}.
 */
public class FacadeImpl implements Facade {

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
	 * Instancia um novo {@link Facade}.
	 * @param adminDao
	 */
	public FacadeImpl(AdminDao adminDao, EmployeeDao employeeDao, CategoryDao categoryDao) {
		this.adminDao = adminDao;
		this.employeeDao = employeeDao;
		this.categoryDao = categoryDao;
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
}

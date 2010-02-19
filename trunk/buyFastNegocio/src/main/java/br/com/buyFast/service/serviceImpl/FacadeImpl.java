package br.com.buyFast.service.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.buyFast.integration.dao.AdminDao;
import br.com.buyFast.integration.dao.EmployeeDao;
import br.com.buyFast.model.Administrator;
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
	 * Instancia um novo {@link Facade}.
	 * @param adminDao
	 */
	public FacadeImpl(AdminDao adminDao, EmployeeDao employeeDao) {
		this.adminDao = adminDao;
		this.employeeDao = employeeDao;
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
	public Employee checkEmployee (String userLogin, String password) throws ServiceException {
		try {
			logger.info("Obtendo funcionário no banco de dados ...");
			return employeeDao.getLoginAndPassword(userLogin, password);
		} catch (Exception e) {
			String messageError = "Erro ao verificar usuário e senha de funcionário.";
			logger.error(messageError, e);
			throw new ServiceException(messageError, e);
		}
	}

}

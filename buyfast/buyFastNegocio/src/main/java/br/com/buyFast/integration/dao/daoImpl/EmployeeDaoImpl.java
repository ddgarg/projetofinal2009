package br.com.buyFast.integration.dao.daoImpl;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

import br.com.buyFast.integration.dao.DaoException;
import br.com.buyFast.integration.dao.EmployeeDao;
import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Employee;

/**
 * Classe DAO que implementa a interface {@link EmployeeDao}.
 */
public class EmployeeDaoImpl extends GenericDaoImpl<Employee, Integer> implements EmployeeDao {

	/**
	 * Apresenta o log na aplicação.
	 */
	private final static Log logger = LogFactory.getLog(EmployeeDaoImpl.class);
	
	public Employee getLoginAndPassword(String user, String password) throws DaoException {
		try  {
			String query = "FROM Employee a WHERE a.user = :user AND a.password = :password ";
			Query q = entityManager.createQuery(query);
			q.setParameter("user", user);
			q.setParameter("user", user);
			return (Administrator) q.getSingleResult();
		} catch (Exception e) {
			String messageError = "Erro ao obter funcionário.";
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}
}

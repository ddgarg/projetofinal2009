package br.com.buyFast.integration.dao.daoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.DaoException;
import br.com.buyFast.integration.dao.EmployeeDao;
import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Employee;

/**
 * Classe DAO que implementa a interface {@link EmployeeDao}.
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRES_NEW)
public class EmployeeDaoImpl extends GenericDaoImpl<Employee, Integer> implements EmployeeDao {

	/**
	 * Apresenta o log na aplicação.
	 */
	private final static Log logger = LogFactory.getLog(EmployeeDaoImpl.class);
	
	public Employee getLoginAndPassword(String user, String password) throws DaoException {
		try  {
			logger.info("Obtendo funcionário através de usuário e senha ...");
			return (Administrator) getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(Employee.class)
					.add(Restrictions.ilike("user", user))
					.add(Restrictions.ilike("password", password)).uniqueResult();
		} catch (Exception e) {
			String messageError = "Erro ao obter funcionário.";
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}
}

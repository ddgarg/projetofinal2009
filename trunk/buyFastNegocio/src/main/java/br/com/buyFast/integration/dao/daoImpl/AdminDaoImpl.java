package br.com.buyFast.integration.dao.daoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.AdminDao;
import br.com.buyFast.integration.dao.DaoException;
import br.com.buyFast.model.Administrator;

/**
 * Classe DAO que implementa a interface {@link AdminDao}.
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRES_NEW)
public class AdminDaoImpl extends GenericDaoImpl<Administrator, Integer> implements AdminDao {

	/**
	 * Apresenta o log na aplicação.
	 */
	private final static Log logger = LogFactory.getLog(AdminDaoImpl.class);
	
	public Administrator getLoginAndPassword(String user, String password) throws DaoException {
		try  {
			logger.info("Obtendo administrador através de usuário e senha ...");
			return (Administrator) getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(Administrator.class)
					.add(Restrictions.ilike("user", user))
					.add(Restrictions.ilike("password", password)).uniqueResult();
		} catch (Exception e) {
			String messageError = "Erro ao obter administrador.";
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}
}

package br.com.buyFast.integration.dao.daoImpl;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import br.com.buyFast.integration.dao.AdminDao;
import br.com.buyFast.integration.dao.DaoException;
import br.com.buyFast.model.Administrator;

/**
 * Classe DAO que implementa a interface {@link AdminDao}.
 */
public class AdminDaoImpl extends GenericDaoImpl<Administrator, Integer> implements AdminDao {

	/**
	 * Apresenta o log na aplicação.
	 */
	private final static Log logger = LogFactory.getLog(AdminDaoImpl.class);
	
	public Administrator getLoginAndPassword(String user, String password) throws DaoException {
		try  {
			logger.info("Obtendo administrador através de usuário e senha ...");
			
			String query = "FROM Administrator a WHERE a.user = :user AND a.password = :password ";
			Query q = entityManager.createQuery(query);
			q.setParameter("user", user);
			q.setParameter("user", user);
			return (Administrator) q.getSingleResult();
		} catch (Exception e) {
			String messageError = "Erro ao obter administrador.";
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}
}

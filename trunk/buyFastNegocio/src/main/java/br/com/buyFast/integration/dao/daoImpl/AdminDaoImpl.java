package br.com.buyFast.integration.dao.daoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

import br.com.buyFast.integration.dao.AdminDao;
import br.com.buyFast.integration.dao.DaoException;
import br.com.buyFast.integration.util.ConnectionFactory;
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
			logger.info("Iniciando sessão do hibernate ...");
			this.session = ConnectionFactory.getIntance();
			logger.info("Obtendo administrador através de usuário e senha ...");
			return (Administrator) session.createCriteria(Administrator.class)
				.add(Restrictions.ilike("user", user))
				.add(Restrictions.ilike("password", password)).uniqueResult();
		} catch (Exception e) {
			String messageError = "Erro ao obter administrador.";
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}
}

package br.com.buyFast.integration.dao.daoImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.buyFast.integration.dao.CategoryDao;
import br.com.buyFast.integration.dao.DaoException;
import br.com.buyFast.integration.util.ConnectionFactory;
import br.com.buyFast.model.Category;

/**
 * Classe DAO que implementa a interface {@link CategoryDao}.
 */
public class CategoryDaoImpl extends GenericDaoImpl<Category, Integer> implements CategoryDao {
	
	private static final Log logger = LogFactory.getLog(CategoryDaoImpl.class);
	
	public List<Category> allCategories() throws DaoException {
		try {
			logger.info("Iniciando sessão ...");
			this.session = ConnectionFactory.getIntance();
			logger.info("Executando query de Obtenção de categoria...");
			return session.createQuery("FROM Category").list();
		} catch (Exception e) {
			String messageError = "Erro ao executar query para " + getObjectClass().getSimpleName();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}  finally {
			this.session.clear();
			logger.info("fechando sessão...");
			this.session.close();
		}
	}

}

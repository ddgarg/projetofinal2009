package br.com.buyFast.integration.dao.daoImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.buyFast.integration.dao.DaoException;
import br.com.buyFast.integration.dao.ProductDao;
import br.com.buyFast.model.Product;

/**
 * Classe DAO que implementa a interface {@link ProductDao}.
 */
public class ProductDaoImpl extends GenericDaoImpl<Product, Integer> implements ProductDao {

	/**
	 * Apresenta o log na aplicação.
	 */
	private static final Log logger = LogFactory.getLog(ProductDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<Product> productSearch(String keyWord) throws DaoException {
		try {
			logger.info("Obter lista de produtos pela chave " + keyWord + "...");
			return getSession().createCriteria(Product.class)
			.add(Restrictions.ilike("name", keyWord, MatchMode.ANYWHERE))
			.add(Restrictions.ilike("smallDescription", keyWord, MatchMode.ANYWHERE))
			.list();
		} catch (Exception e) {
			String error = "Erro ao obter lista de produto pela chave " + keyWord;
			logger.error(error, e);
			throw new DaoException(error, e);
		}
	}
}

package br.com.buyFast.integration.daoImpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.IntegrationException;
import br.com.buyFast.integration.dao.GenericDao;

/**
 * Classe que implementa a classe {@link GenericDao}.
 * @param <T> O tipo do DAO.
 * @param <ID> {@link Serializable}.
 */
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

	/**
	 * Apresenta o log na aplicação.
	 */
	private final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * O entityManager para a persistência de dados.
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * A classe tipo atual.
	 */
	private final Class<T> _class;
	
	/**
	 * Instancia um novo {@link GenericDao}.
	 */
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this._class = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass())
			.getActualTypeArguments() [0];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> all() throws IntegrationException {
		try {
			String queryString = "SELECT obj from " + _class.getSimpleName() + " obj";
			return entityManager.createQuery(queryString).getResultList();
		} catch (Exception e) {
			logger.error("Erro ao obter lista de " + _class.getSimpleName(), e);
			throw new IntegrationException(e);
		}
	}

	@Override
	public void delete(Object obj) throws IntegrationException {
		try {
			entityManager.remove(obj);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Erro ao remover objeto " + _class.getSimpleName(), e);
			throw new IntegrationException(e);
		}
	}

	@Override
	public Class<T> getObjectClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> listSearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> listSearchParam(String query, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> listSearchParam(String query, Map<String, Object> params,
			int maximun, int current) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T save(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T searchById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T searchParam(String query, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obter o entityManager.
	 * @return o entityManager.
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Ajustar o entityManager.
	 * @param entityManager o entityManager.
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Obter o tipo de classe atual.
	 * @return o tipo de classe atual.
	 */
	public Class<T> get_class() {
		return _class;
	}

}

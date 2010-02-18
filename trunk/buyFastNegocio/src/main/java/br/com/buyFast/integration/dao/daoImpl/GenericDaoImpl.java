package br.com.buyFast.integration.dao.daoImpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.DaoException;
import br.com.buyFast.integration.dao.GenericDao;
import br.com.buyFast.integration.util.ConnectionFactory;

/**
 * Classe DAO genérica que implementa a interface {@link GenericDao}.
 * @param <T>
 * @param <ID>
 */
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

	/**
	 * Apresenta o log na aplicação.
	 */
	private static final Log logger = LogFactory.getLog("GenericDaoImpl");
	
	/**
	 * Representa o serviço de persistência do hibernate;
	 */
	Session session;
	
	/**
	 * Responsável pelas transações de persistência.
	 */
	Transaction transaction;
	
	/**
	 * A classe do objeto.
	 */
	private final Class<T> $Class;
	
	/**
	 * Instancia um novo {@link GenericDaoImpl}.
	 */
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
		this. $Class = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];
		} else  {
			$Class = (Class<T>) getClass().getGenericSuperclass();
		}
	}
	
	@Override
	public Class<T> getObjectClass() {
		return this.$Class;
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public T save(T object) throws DaoException {
		try {
			logger.info("Iniciando sessão ...");
			this.session = ConnectionFactory.getIntance();
			logger.info("Iniciando transação ...");
			this.transaction = session.beginTransaction();
			logger.info("Salvando objeto " + object + "...");
			session.save(object);
			transaction.commit();
			logger.info("Objeto " + getObjectClass().getSimpleName() + " salvo com sucesso.");
		} catch (Exception e) {
			String messageError = "Erro ao salvar objeto " + getObjectClass().getSimpleName();
			transaction.rollback();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		} finally {
			this.session.clear();
			logger.info("fechando sessão...");
			this.session.close();
		}
		
		return object;
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public T update(T object) throws DaoException {
		try {
			logger.info("Iniciando sessão ...");
			this.session = ConnectionFactory.getIntance();
			logger.info("Iniciando transação ...");
			this.transaction = session.beginTransaction();
			logger.info("Atualizando objeto " + object + "...");
			session.update(object);
			transaction.commit();
			logger.info("Objeto " + getObjectClass().getSimpleName() + " atualizado com sucesso.");
		} catch (Exception e) {
			String messageError = "Erro ao atualizar objeto " + getObjectClass().getSimpleName();
			transaction.rollback();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		} finally {
			this.session.clear();
			logger.info("fechando sessão...");
			this.session.close();
		}
		
		return object;
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void delete(T object) throws DaoException {
		try {
			logger.info("Iniciando sessão ...");
			this.session = ConnectionFactory.getIntance();
			logger.info("Iniciando transação ...");
			this.transaction = session.beginTransaction();
			logger.info("Removendo objeto " + object + "...");
			session.delete(object);
			transaction.commit();
			logger.info("Objeto " + getObjectClass().getSimpleName() + " removido com sucesso.");
		} catch (Exception e) {
			String messageError = "Erro ao remover objeto " + getObjectClass().getSimpleName();
			transaction.rollback();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		} finally {
				this.session.clear();
				logger.info("fechando sessão...");
				this.session.close();
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listSearch(String query) throws DaoException {
		try {
			logger.info("Iniciando sessão ...");
			this.session = ConnectionFactory.getIntance();
			logger.info("Executando query e retornando lista de resultados ...");
			return (List<T>) session.createQuery(query).list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listSearchParam(String query, Map<String, Object> params) throws DaoException {
		try {
			logger.info("Iniciando sessão ...");
			this.session = ConnectionFactory.getIntance();
			logger.info("Executando query ...");
			Query q = session.createQuery(query);
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
			logger.info("Retornando lista de resultados...");
			return (List<T>) q.list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listSearchParam(String query, Map<String, Object> params, int maximum, int current) 
			throws DaoException {
		try {
			logger.info("Iniciando sessão ...");
			this.session = ConnectionFactory.getIntance();
			logger.info("Executando query ...");
			Query q = session.createQuery(query).setMaxResults(maximum).setFirstResult(current);
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
			logger.info("Retornando lista de resultados...");
			return (List<T>) q.list();
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

	@SuppressWarnings("unchecked")
	@Override
	public T searchById(ID id) throws DaoException {
		try {
			logger.info("Iniciando sessão ...");
			this.session = ConnectionFactory.getIntance();
			logger.info("Obtem objeto com ID = " + id);
			return (T) session.load($Class, id);
		}catch (Exception e) {
			String messageError = "Erro ao carregar objeto do tipo " + getObjectClass().getSimpleName();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}  finally {
			this.session.clear();
			logger.info("fechando sessão...");
			this.session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T searchParam(String query, Map<String, Object> params) throws DaoException {
		try {
			logger.info("Iniciando sessão ...");
			this.session = ConnectionFactory.getIntance();
			logger.info("Executando query ...");
			Query q = session.createQuery(query);
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
			logger.info("Retornando lista de resultados...");
			return (T) q.uniqueResult();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<T> all() throws DaoException {
		try {
			logger.info("Iniciando sessão ...");
			this.session = ConnectionFactory.getIntance();
			logger.info("Obtendo todos os objetos do tipo " + getObjectClass().getSimpleName() + "...");
			return session.createCriteria($Class).list();
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

package br.com.buyFast.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.buyFast.util.ConnectionFactory;

/**
 * Classe responsável pelo acesso ao dados.
 * Deverá ser passado o tipo da classe.
 * @param <T> - O tipo da classe.
 */
public class GenericDaoImp<T> implements GenericDao<T>, Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Responsável pelo log da aplicação.
	 */
	private static final Log logger = LogFactory.getLog(GenericDaoImp.class);
	
	/**
	 * Sessão do hibernate.
	 */
	private Session session;
	
	public void remove(T obj) {
		logger.debug("Iniciando a remoção do dado...");
		session = ConnectionFactory.getIntance();
		Transaction tx = null;
		try {
			logger.debug("Iniciando transação...");
			tx = session.beginTransaction();
			logger.debug("Deletando objeto...");
			session.delete(obj);
			tx.commit();
			logger.debug("Objeto removido do banco.");
		} catch (Exception e) {
			logger.error("Erro ao remover objeto.", e);
			tx.rollback();
			logger.debug("Realizado o rollback.");
		} finally {
			logger.debug("Fechando sessão.");
			session.close();
		}
	}

	public void save(T obj) {
		logger.debug("Iniciando a persistência do dado...");
		session = ConnectionFactory.getIntance();
		Transaction tx = null;
		try {
			logger.debug("Iniciando transação...");
			tx = session.beginTransaction();
			logger.debug("Salvando objeto...");
			session.save(obj);
			tx.commit();
			logger.debug("Objeto persistido no banco.");
		} catch (Exception e) {
			logger.error("Erro ao salvar objeto.", e);
			tx.rollback();
			logger.debug("Realizado o rollback.");
		} finally {
			logger.debug("Fechando sessão.");
			session.close();
		}
	}

	public void update(T obj) {
		logger.debug("Iniciando o upgade do objeto...");
		session = ConnectionFactory.getIntance();
		Transaction tx = null;
		try {
			logger.debug("Iniciando transação...");
			tx = session.beginTransaction();
			logger.debug("Upgade no objeto...");
			session.update(obj);
			tx.commit();
			logger.debug("Objeto atualizado no banco.");
		} catch (Exception e) {
			logger.error("Erro ao atualizar objeto.", e);
			tx.rollback();
			logger.debug("Realizado o rollback.");
		} finally {
			logger.debug("Fechando sessão.");
			session.close();
		}
	}

	public T load(T obj) {
		session = ConnectionFactory.getIntance();
        Transaction tx = null;
        List<Object> list = null;
        try {
        	tx = session.beginTransaction();
        	list = session.createQuery("from " + obj.getClass().getSimpleName() + " o").list();
        	tx.commit();
		} catch (Exception e) {
			logger.error("Erro ao obter objeto.", e);
			tx.rollback();
		} finally {
			session.close();
		}
		return (T) list.get(0);
	};
}

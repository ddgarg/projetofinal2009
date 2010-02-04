package br.com.buyFast.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Classe responsável por instanciar e abrir as sessões do hibernate.
 */
public class ConnectionFactory {
	
	/**
	 * Responsável por apresentar os log da aplicação.
	 */
	private static final Log logger = LogFactory.getLog(ConnectionFactory.class);
	
	/**
	 * Fábrica de sessões.
	 */
	private static final SessionFactory sessionFactory;
	
	/**
	 * Thread onde será executada as sessões do hibernate. 
	 */
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	static {
		try {
			logger.debug("Criando a fábrica de sessões...");
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
			logger.debug("Configuração das anotações ok.");
		} catch (Throwable t) {
			logger.error("Erro ao iniciar a fábrica de sessões.", t);
			throw new ExceptionInInitializerError(t);
		}
	}

	/**
	 * Responsável por abrir a sessão do hibernate.
	 * @return a sessão do hibernate.
	 */
	public static Session getIntance() {
		Session session = threadLocal.get();
		logger.debug("Abrindo sessão do hinernate...");
		session = sessionFactory.openSession();
		threadLocal.set(session);
		return session;
	}
}

package br.com.buyFast.integration.dao.daoImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.GenericDao;
import br.com.buyFast.integration.util.ConnectionFactory;

/**
 * Classe dao genérica que implementa a interface {@link GenericDao}.
 * @param <T>
 * @param <ID>
 */
@Transactional(readOnly=true, propagation=Propagation.REQUIRES_NEW)
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

	/**
	 * Apresenta o log na aplicação.
	 */
	private static final Log logger = LogFactory.getLog("GenericDaoImpl");
	
	@Override
	public T save(T object) {
		// TODO Auto-generated method stub
		ConnectionFactory.getIntance();
		return null;
	}
	
	@Override
	public T update(T object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void delete(T object) {
		// TODO Auto-generated method stub
		
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
			int maximum, int current) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T searchById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T searchParam(String query, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> all() {
		// TODO Auto-generated method stub
		return null;
	}

}

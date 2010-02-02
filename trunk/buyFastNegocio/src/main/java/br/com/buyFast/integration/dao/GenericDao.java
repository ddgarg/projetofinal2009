package br.com.buyFast.integration.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.buyFast.integration.IntegrationException;

/**
 * Classe que representa a camada de acesso a dados da aplicação.
 * @param <T> O tipo do DAO.
 * @param <ID> {@link Serializable}
 */
public interface GenericDao<T, ID extends Serializable> {

	Class<T> getObjectClass();
	T save(Object obj);
	T searchById(long id);
	T update(Object obj);
	void delete(Object obj) throws IntegrationException;
	List<T> all() throws IntegrationException;
	List<T> listSearchParam(String query, Map<String , Object> params);
	List<T> listSearchParam(String query, Map<String , Object> params, int maximun, int current);
	List<T> listSearch(String query);
	T searchParam(String query, Map<String , Object> params);
}

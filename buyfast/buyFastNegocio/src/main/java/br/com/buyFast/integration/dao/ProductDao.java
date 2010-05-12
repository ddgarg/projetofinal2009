package br.com.buyFast.integration.dao;

import java.util.List;

import br.com.buyFast.model.Product;

/**
 * Classe que representa o objeto de acesso a dados para o modelo {@link Product}.
 */
public interface ProductDao extends GenericDao<Product, Integer> {

	/**
	 * Obter os produtos a partir da palavra-chave passada.
	 * @param keyWord a palavra chave do produto.
	 * @return os produtos a partir da palavra-chave passada.
	 * @throws DaoException
	 */
	List<Product> productSearch(String keyWord) throws DaoException;

}

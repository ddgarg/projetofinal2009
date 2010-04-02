package br.com.buyFast.integration.dao;

import java.util.List;

import br.com.buyFast.model.Category;

/**
 * Classe que representa o objeto de acesso a dados para o modelo {@link Category}.
 */
public interface CategoryDao extends GenericDao<Category, Integer> {

	/**
	 * Método utilizado para obter todas as categorias da base de dados.
	 * @return Lista de categoria da base de dados.
	 * @throws DaoException
	 */
	List<Category> allCategories() throws DaoException;

}

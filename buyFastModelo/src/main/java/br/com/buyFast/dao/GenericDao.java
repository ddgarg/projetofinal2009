package br.com.buyFast.dao;

/**
 * Classe que representa o objeto de acesso a dados.
 * 
 * @param <T> O tipo de modelo utilizado pelo DAO.
 */
public interface GenericDao<T> {

	/**
	 * Persiste o objeto no banco de dados.
	 * @param obj o objeto a ser persistido
	 */
	void save(T obj);
	
	/**
	 * Remove o objeto no banco de dados.
	 * @param obj o objeto a ser removido.
	 */
	void remove(T obj);
	
	/**
	 * Atualiza o objeto no banco de dados.
	 * @param obj o objeto a ser atualizado.
	 */
	void update(T obj);
	
	/**
	 * Realiza o refresh do objeto persistido. O objeto é atualizado conforme informações 
	 * do banco de dados.
	 * @param obj o objeto a ser atualizado em memória.
	 * @return o objeto atualizado a partir do banco de dados.
	 */
	T load(T obj);
}

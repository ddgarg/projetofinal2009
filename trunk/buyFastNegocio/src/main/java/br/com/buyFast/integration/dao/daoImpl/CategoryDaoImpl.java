package br.com.buyFast.integration.dao.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.CategoryDao;
import br.com.buyFast.model.Category;

/**
 * Classe DAO que implementa a interface {@link CategoryDao}.
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class CategoryDaoImpl extends GenericDaoImpl<Category, Integer> implements CategoryDao {
	
}

package br.com.buyFast.integration.dao.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.ProductDao;
import br.com.buyFast.model.Product;

/**
 * Classe DAO que implementa a interface {@link ProductDao}.
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRES_NEW)
public class ProductDaoImpl extends GenericDaoImpl<Product, Integer> implements ProductDao {

}

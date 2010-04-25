package br.com.buyFast.integration.dao.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.OrderDao;
import br.com.buyFast.model.Order;

/**
 * Classe DAO que implementa a interface {@link OrderDao}.
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class OrderDaoImpl extends GenericDaoImpl<Order, Integer> implements OrderDao {

}

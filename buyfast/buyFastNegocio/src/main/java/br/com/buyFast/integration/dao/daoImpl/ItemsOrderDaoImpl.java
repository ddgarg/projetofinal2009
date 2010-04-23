package br.com.buyFast.integration.dao.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.ItemsOrderDao;
import br.com.buyFast.model.ItemsOrder;

/**
 * Classe DAO que implementa a interface {@link ItemsOrderDao}.
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRES_NEW)
public class ItemsOrderDaoImpl extends GenericDaoImpl<ItemsOrder, Integer> implements ItemsOrderDao {

}

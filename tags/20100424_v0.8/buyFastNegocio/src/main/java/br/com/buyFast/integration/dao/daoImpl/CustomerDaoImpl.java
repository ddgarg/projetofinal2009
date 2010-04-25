package br.com.buyFast.integration.dao.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.CustomerDao;
import br.com.buyFast.model.Customer;

/**
 * Classe DAO que implementa a interface {@link CustomerDaoImpl}.
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class CustomerDaoImpl extends GenericDaoImpl<Customer, Integer> implements CustomerDao {

}

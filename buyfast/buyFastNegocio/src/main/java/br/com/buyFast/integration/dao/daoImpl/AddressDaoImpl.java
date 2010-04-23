package br.com.buyFast.integration.dao.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.AddressDao;
import br.com.buyFast.model.Address;

/**
 * Classe DAO que implementa a interface {@link AddressDao}.
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRES_NEW)
public class AddressDaoImpl extends GenericDaoImpl<Address, Integer> implements AddressDao {

}

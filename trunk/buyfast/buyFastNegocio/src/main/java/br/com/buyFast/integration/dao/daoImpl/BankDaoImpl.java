package br.com.buyFast.integration.dao.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.BankDao;
import br.com.buyFast.model.Bank;

/**
 * Classe DAO que implementa a interface {@link BankDao}.
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRES_NEW)
public class BankDaoImpl extends GenericDaoImpl<Bank, Integer> implements BankDao {

}

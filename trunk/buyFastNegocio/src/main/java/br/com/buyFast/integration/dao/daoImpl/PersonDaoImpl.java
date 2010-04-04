package br.com.buyFast.integration.dao.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.PersonDao;
import br.com.buyFast.model.Person;

/**
 * Classe DAO que implementa a interface {@link PersonDao}.
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class PersonDaoImpl extends GenericDaoImpl<Person, Integer> implements PersonDao {

}

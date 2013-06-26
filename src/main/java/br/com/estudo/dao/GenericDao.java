package br.com.estudo.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> extends Serializable {

    void save(T obj);
    void update(T obj);
    void delete(T obj);
    T findById(ID id);
    List<T> findAll();
    List<T> findByNamedQuery(String namedQuery);
    void flush();
}

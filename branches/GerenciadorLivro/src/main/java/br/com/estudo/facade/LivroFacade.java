package br.com.estudo.facade;

import java.io.Serializable;
import java.util.List;

import br.com.estudo.domain.Livro;


public interface LivroFacade extends Serializable {

    void save(Livro livro);
    void update(Livro livro);
    void delete(Livro livro);
    List<Livro> findAll();

}

package br.com.estudo.dao.impl;

import org.springframework.stereotype.Service;

import br.com.estudo.dao.LivroDao;
import br.com.estudo.domain.Livro;

@Service("livroDao")
public class LivroDaoImpl extends GenericDaoImpl<Livro, Long> implements LivroDao {

    private static final long serialVersionUID = 1L;

}

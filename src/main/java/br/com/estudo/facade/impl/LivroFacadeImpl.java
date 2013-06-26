package br.com.estudo.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.estudo.dao.LivroDao;
import br.com.estudo.domain.Livro;
import br.com.estudo.facade.LivroFacade;

@Service("livroFacade")
@Primary
public class LivroFacadeImpl implements LivroFacade {

    private static final long serialVersionUID = 1323586554296275441L;

    @Autowired
    private LivroDao livroDao;
    
    @Override
    public void save(Livro livro) {
        livroDao.save(livro);
    }

    @Override
    public void update(Livro livro) {
        livroDao.update(livro);
    }

    @Override
    public void delete(Livro livro) {
        livroDao.delete(livro);
    }

    @Override
    public List<Livro> findAll() {
        return livroDao.findByNamedQuery("livro.all");
    }

}

package br.com.estudo.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import br.com.estudo.domain.Livro;
import br.com.estudo.facade.LivroFacade;

@ManagedBean
@RequestScoped
@Controller("livroController")
@Component
public class LivroController implements Serializable {

    private static final long serialVersionUID = 4292999630364250482L;

    private static final String GERENCIAR_LIVRO = "gerenciarLivro";
    private static final String HOME = "home";

    private Livro livro = new Livro();
    private DataModel<Livro> listaLivros;
    
    @Autowired
    private LivroFacade livroFacade;


    public LivroController() {
        super();
    }

    public String prepararAdicionarLivro() {
        livro = new Livro();
        return GERENCIAR_LIVRO;
    }

    public String prepararAlterarLivro() {
        livro = (listaLivros.getRowData());
        return GERENCIAR_LIVRO;
    }

    public String excluirLivro() {
        Livro livroTemp = (listaLivros.getRowData());
        livroFacade.delete(livroTemp);
        return HOME;
    }

    public String adicionarLivro() {
        livroFacade.save(livro);
        return HOME;
    }

    public String alterarLivro() {
        livroFacade.update(livro);
        return HOME;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public DataModel<Livro> getListaLivros() {
        this.listaLivros = new ListDataModel<Livro>(livroFacade.findAll());
        return this.listaLivros;
    }

    public void setListaLivros(DataModel<Livro> listaLivros) {
        this.listaLivros = listaLivros;
    }

}

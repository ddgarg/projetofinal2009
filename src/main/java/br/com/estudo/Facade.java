package br.com.estudo;

import java.io.Serializable;

import br.com.estudo.lang.FacadeException;
import br.com.estudo.modelo.Usuario;

public interface Facade extends Serializable {

    public String gerarToken(String ... values);

	Usuario getUsuarioByLogin(String login) throws FacadeException;

	void salvarUsuario(Usuario usuario) throws FacadeException;

	Usuario getUsuarioPorId(Long id) throws FacadeException;
    
}

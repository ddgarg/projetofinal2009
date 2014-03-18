package br.com.estudo.dao;

import br.com.estudo.lang.DaoException;
import br.com.estudo.modelo.Usuario;

public interface UsuarioDao extends GenericDao<Usuario, Long> {

	Usuario searchByLogin(String login) throws DaoException;

}

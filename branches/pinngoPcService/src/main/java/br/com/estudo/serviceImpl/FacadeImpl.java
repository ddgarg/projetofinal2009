package br.com.estudo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import br.com.estudo.Facade;
import br.com.estudo.dao.UsuarioDao;
import br.com.estudo.lang.DaoException;
import br.com.estudo.lang.FacadeException;
import br.com.estudo.modelo.Usuario;

@Service("facade")
public class FacadeImpl implements Facade {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Override
    public Usuario getUsuarioPorId(Long id) throws FacadeException {
    	try {
			return usuarioDao.searchById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new FacadeException(e);
		}
    }
    
    @Override
	public void salvarUsuario(Usuario usuario) throws FacadeException {
    	try {
			usuarioDao.save(usuario);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new FacadeException(e);
		}
    }
    
    @Override
	public Usuario getUsuarioByLogin(String login) throws FacadeException {
    	try {
			return usuarioDao.searchByLogin(login);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new FacadeException(e);
		}
    }
    
    @Override
	public String gerarToken(String... values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Parametros não podem ser nulos ou vazios!");
        }
        String keySource = null;
        
        for (String value : values) {
            keySource += value;
        }

        byte[] tokenByte = Base64.encode(keySource.getBytes());
        
        String token = new String(tokenByte);
        
        return token;
    }
}

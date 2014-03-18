package br.com.estudo;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import br.com.estudo.lang.FacadeException;
import br.com.estudo.modelo.Pesquisa;
import br.com.estudo.modelo.ReturnMessage;
import br.com.estudo.modelo.Usuario;

@Component
@Path("service")
public class ServiceResource {

	@Inject
    private Facade facade;
    
	@GET()
	@Path("/teste")
	@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
	public String sayHello() {
	    return "Hello World!";
	}
	
	@GET()
    @Path("/login/{login}/{senha}")
    @Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public ReturnMessage checkLogin(@PathParam("login") String login, @PathParam("senha") String senha) {
		Usuario usuario = new Usuario("Daniel", "admin@pinngo.com", "admin", "");
		try {
			facade.salvarUsuario(usuario);
		} catch (FacadeException e1) {
			e1.printStackTrace();
		}
		
		ReturnMessage returnMessage = null;
		
		usuario = null;
		
		try {
			usuario = facade.getUsuarioByLogin(login);

			if (usuario != null && login.equals(usuario.getLogin()) && senha.equals(usuario.getSenha())) {
				returnMessage = new ReturnMessage(true, "sucesso", facade.gerarToken(login,"12/04/2014"));
			} else {
				returnMessage = new ReturnMessage(false, "Login ou senha inválidos", "");
			}
		} catch (FacadeException e) {
			e.printStackTrace();
			returnMessage = new ReturnMessage(false, e.getMessage(), "");
		}
		
		return returnMessage;
    }
	
	@GET()
    @Path("/pesquisas/{login}/{token}")
    @Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public Pesquisa getPesquisaUsusario(@PathParam("login") String login, @PathParam("token") String token) {
	    Pesquisa pesquisa = new Pesquisa();
	    return pesquisa;
    }
}

package br.com.caelum.aeris.autentication;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.bpm.Actor;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.com.caelum.aeris.entity.Role;
import br.com.caelum.aeris.entity.User;

@Name("autenticador")
@Scope(ScopeType.APPLICATION)
public class Autenticador implements Serializable {

	private static final long serialVersionUID = -3334272871657291277L;

	@In
	private Identity identity;
	
	@In
	private Actor actor;
	
	@In
	private Credentials credentials;
	
	@In
	private FacesMessages facesMessages;
	
	@Logger
	private Log log;
	
	@In
	private EntityManager entityManager;
	
	private Map<String, String> roles;
	
	public Autenticador() {
		super();
		this.roles = new HashMap<String, String>();
		this.roles.put("aeris", "empresa");
		this.roles.put("cliente", "comprador");
	}

	@Begin(pageflow="login")
	public boolean autenticar() {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		
		log.info("Autenticando #0", username);
		
		@SuppressWarnings("unchecked")
		List<User> listUser = entityManager.createNamedQuery("User.login")
				.setParameter("login", username)
				.setParameter("senha", password)
				.getResultList();
		
		User user = null;
		
		if (listUser.isEmpty()) {
			log.warn("Login ou senha incorretos.");
			return false;
		} else {
			user = listUser.get(0);
		}
		
		if (user != null) {
			for (Role role : user.getRoles()) {
				identity.addRole(role.getType());
				actor.getGroupActorIds().add(role.getType());
			}
			actor.setId(username);
			return true;
		}
		
		return false;
	}
	
	public String logout() {
		credentials.invalidate();
		identity.unAuthenticate();
		return "/login.xhtml";
	}
	
	public boolean isEmpresa() {
		return identity.hasRole("empresa");
	}
	
	public boolean isComprador() {
		return identity.hasRole("comprador");
	}
}

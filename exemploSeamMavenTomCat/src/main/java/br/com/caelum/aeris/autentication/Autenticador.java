package br.com.caelum.aeris.autentication;

import java.util.HashMap;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

@Name("autenticador")
@Scope(ScopeType.APPLICATION)
public class Autenticador {

	@In
	private Identity identity;
	
	@In
	private Credentials credentials;
	
	@Logger
	Log log;
	
	private Map<String, String> roles;
	
	public Autenticador() {
		super();
		this.roles = new HashMap<String, String>();
		this.roles.put("aeris", "empresa");
		this.roles.put("cliente", "comprador");
	}

	public boolean autenticar() {
		String username = credentials.getUsername();
		log.info("Autenticando #0", username);
		if ("aeris".equals(username) || "cliente".equals(username)) {
			identity.addRole(this.roles.get(username));
			return true;
		}
		return false;
	}
	
	public String logout() {
		credentials.invalidate();
		identity.unAuthenticate();
		return "/home.xhtml";
	}
	
}

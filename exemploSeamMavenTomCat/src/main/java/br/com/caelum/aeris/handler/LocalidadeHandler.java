package br.com.caelum.aeris.handler;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.caelum.aeris.entity.enumerator.Localidade;

@Name("localidadeHandler")
public class LocalidadeHandler {

	@Logger
	private Log log;
	
	@Factory(value = "localidades", scope = ScopeType.APPLICATION)
	public Localidade[] initLocalidades() {
		log.info("Iniciando Localidades");
		return Localidade.values();
	}
}

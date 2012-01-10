package br.com.caelum.aeris.handler;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.caelum.aeris.entity.enumerator.Localidade;

@Name("localidadeHandler")
public class LocalidadeHandler implements Serializable {

	private static final long serialVersionUID = 853566460855346059L;

	@Logger
	private Log logger;
	
	@Factory(value = "localidades", scope = ScopeType.APPLICATION)
	public Localidade[] initLocalidades() {
		logger.info("Carregando localidades.", new Object());
		return Localidade.values();
	}
}

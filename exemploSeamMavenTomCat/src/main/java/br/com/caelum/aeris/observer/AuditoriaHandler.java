package br.com.caelum.aeris.observer;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import br.com.caelum.aeris.entity.Trecho;

@Name("auditoria")
@Scope(ScopeType.EVENT)
public class AuditoriaHandler {

	@Logger
	private Log log;
	
	@Observer(value={"novoTrecho"})
	public void registraQuetrechoFoiAdicionado(Trecho trecho) {
		log.info("Novo Trecho registrado! #0", trecho);
	}
	
}

package br.com.caelum.aeris.handler;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;

import br.com.caelum.aeris.entity.enumerator.Localidade;

@Name("localidadeHandler")
public class LocalidadeHandler implements Serializable {

	private static final long serialVersionUID = 853566460855346059L;

	@Factory(value = "localidades", scope = ScopeType.APPLICATION)
	public Localidade[] initLocalidades() {
		return Localidade.values();
	}
}

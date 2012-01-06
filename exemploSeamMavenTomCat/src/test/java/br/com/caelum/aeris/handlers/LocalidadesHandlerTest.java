package br.com.caelum.aeris.handlers;


import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.caelum.aeris.entity.enumerator.Localidade;
import br.com.caelum.aeris.handler.LocalidadeHandler;

public class LocalidadesHandlerTest extends TestCase {

	@Test
	public void testDeveFornecerTodasAsLocalidades() {
		List<Localidade> todas = Arrays.asList(Localidade.values());
		
		LocalidadeHandler localidadeHandler = new LocalidadeHandler();
		List<Localidade> localidades = Arrays.asList(localidadeHandler.initLocalidades());
		
		assertEquals(todas, localidades);
	}

}

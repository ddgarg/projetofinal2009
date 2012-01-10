package br.com.caelum.aeris.handlers;


import java.util.Arrays;
import java.util.List;

import org.jboss.seam.mock.SeamTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.caelum.aeris.entity.enumerator.Localidade;
import br.com.caelum.aeris.handler.LocalidadeHandler;

@RunWith(JUnit4.class)
public class LocalidadesHandlerTest extends SeamTest {

	@Test
	public void testDeveFornecerTodasAsLocalidades() {
		List<Localidade> todas = Arrays.asList(Localidade.values());
		
		LocalidadeHandler localidadeHandler = new LocalidadeHandler();
		List<Localidade> localidades = Arrays.asList(localidadeHandler.initLocalidades());
		
		Assert.assertEquals(todas, localidades);
	}

}

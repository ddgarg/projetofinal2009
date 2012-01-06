package br.com.caelum.aeris.handlers;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.aeris.JUnitSeamTest;
import br.com.caelum.aeris.entity.Trecho;
import br.com.caelum.aeris.entity.enumerator.Localidade;

public class CadastroTrechoTest extends JUnitSeamTest {

	@Test
	public void testTrechoPodeSerCadastrado() throws Exception {
		new ComponentTest() {
			@Override
			protected void testComponents() throws Exception {
				setValue("#{trechoHandler.trecho.origem}", Localidade.SAO_PAULO);
				setValue("#{trechoHandler.trecho.destino}", Localidade.RIO_DE_JANEIRO);
				System.out.println("passei");
				Assert.assertNull(invokeMethod("#{trechoHandler.salvar}"));
				
				// Limpou campos após salvar
				Assert.assertNull(getValue("#{trechoHandler.trecho.origem}"));
				Assert.assertNull(getValue("#{trechoHandler.trecho.destino}"));
				
				// Entrou na lista de trechos
				@SuppressWarnings("unchecked")
				List<Trecho> trechos = (List<Trecho>) getValue("#{trechos}");
				Assert.assertEquals(1, trechos.size());
				Assert.assertEquals(Localidade.SAO_PAULO, trechos.get(0).getOrigem());
				Assert.assertEquals(Localidade.RIO_DE_JANEIRO, trechos.get(0).getDestino());
			}
		};
	}
}
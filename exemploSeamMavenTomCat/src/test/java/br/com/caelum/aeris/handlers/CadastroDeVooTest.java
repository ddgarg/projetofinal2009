package br.com.caelum.aeris.handlers;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.aeris.JUnitSeamTest;
import br.com.caelum.aeris.entity.Voo;

public class CadastroDeVooTest extends JUnitSeamTest {

	@Test
	public void testVooPodeSerCadastrado() throws Exception {
		final StringBuilder conversationId = new StringBuilder();
		
		new FacesRequest("/voos.xhtml") {
			protected void invokeApplication() throws Exception {
				invokeAction("#{vooHandler.manipulaVoos(trecho)}");
				Assert.assertTrue(isLongRunningConversation());
				conversationId.append(getConversationId());
			}
		};
		
		//Continua na mesma conversation
		new FacesRequest("/voos.xhtml", conversationId.toString()) {
			protected void applyRequestValues() throws Exception {
				// Nada
			}
			
			protected void processValidations() throws Exception {
				validateValue("#{voo.codigo}", "VARIG123");
				validateValue("#{voo.dataChegada}", new Date());
				validateValue("#{voo.horaChegada}", new Date());
				validateValue("#{voo.dataPartida}", new Date());
				validateValue("#{voo.horaPartida}", new Date());
				
				Assert.assertFalse(isValidationFailure());
			}
			
			protected void updateModelValues() throws Exception {
				setValue("#{voo.codigo}", "VARIG123");
				setValue("#{voo.dataChegada}", new Date());
				setValue("#{voo.horaChegada}", new Date());
				setValue("#{voo.dataPartida}", new Date());
				setValue("#{voo.horaPartida}", new Date());
			}
			
			protected void invokeApplication() throws Exception {
				Assert.assertTrue(isLongRunningConversation());
				Object outCome = invokeMethod("#{vooHandler.salvarVoo}");
				Assert.assertEquals("/voo.xhtml", outCome);
			}
			
			protected void renderResponse() throws Exception {
				// Limpou valores? (outJact de um voo vazio)
				Assert.assertNull(getValue("#{voo.codigo}"));
				Assert.assertEquals(new Date(), getValue("#{voo.dataPartida}"));
				Assert.assertEquals(new Date(), getValue("#{voo.horaPartida}"));
				Assert.assertEquals(new Date(), getValue("#{voo.dataChegada}"));
				Assert.assertEquals(new Date(), getValue("#{voo.horaChegada}"));
				
				@SuppressWarnings("unchecked")
				List<Voo> voos = (List<Voo>) getValue("#{voos}");
				Assert.assertEquals(1, voos.size());
			}
		}.run();
	}
	
}

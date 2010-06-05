package br.com.buyFast.service;

import br.com.buyFast.model.Order;
import br.com.buyFast.service.generate.GenerateBoleto;


/**
 * Classe responsável pelo serviço de geração de boleto.
 * Essa classe é responsável pela informações que serão geradas pelo boleto.
 * 
 * @see GenerateBoleto
 */
public interface BoletoService {

	/**
	 * Método responsável por gerar o boleto de pagamento.
	 * @param order O pedido ao qual será gerado o boleto.
	 */
	void generateBoleto(Order order);
	
}

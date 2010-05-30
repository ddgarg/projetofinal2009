package br.com.buyFast.service;

import br.com.buyFast.model.Customer;
import br.com.buyFast.model.Order;
import br.com.buyFast.service.generate.GenerateBoleto;


/**
 * Classe responsável pelo serviço de geração de boleto.
 * Essa classe é responsável pela informações que serão geradas pelo boleto.
 * 
 * @see GenerateBoleto
 */
public interface BoletoService {

	String CEDENTE = "";
	String LOCALDEPAGAMENTO = "ATE O VENCIMENTO, PREFERENCIALMENTE NA CAIXA ECONOMICA";
	String LOCALDEPAGAMENTOAPOSVENCIMENTO = "APOS O VENCIMENTO, SOMENTE NA CAIXA ECONOMICA";
	String INSTRUCAO1 = "APOS O VENCIMENTO COBRAR MULTA DE 2%";
	String INSTRUCAO2 = "APOS O VENCIMENTO, COBRAR MULTA DE  R$ 0.50 AO DIA";
	String INSTRUCAO3 = "APOS O VENCIMENTO NÃO APLICAR DESCONTO";
	String INSTRUCAO4 = "APOS O VENCIMENTO COBRAR R$ 1.50 DE TAXA DE OPERAÇÃO ";
	String INSTRUCAO5 = "ANTES DO VENCIMENTO APLICAR DESCONTO DE 5%";
	String DESCRICAO1 = "Boleta referente a cobrança de serviços prestados. ";
	String DESCRICAO2 = "Os serviços de jardinagem foram encerrados no dia 19/12/2009";
	
	/**
	 * Método responsável por gerar o boleto de pagamento.
	 * @param order O pedido ao qual será gerado o boleto.
	 */
	void generateBoleto(Order order);
	
}

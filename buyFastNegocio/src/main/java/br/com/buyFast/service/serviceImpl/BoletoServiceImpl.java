package br.com.buyFast.service.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.buyFast.model.Order;
import br.com.buyFast.service.BoletoService;
import br.com.buyFast.service.generate.GenerateBoleto;

/**
 * Classe responsável por implementar a interface {@link BoletoService}.
 */
public class BoletoServiceImpl implements BoletoService {

	/**
	 * Responsável pela geração de boletos de pagamento.
	 */
	private GenerateBoleto generateBoleto;
	
	/**
	 * Responsável por apresentar o boleto na aplicação.
	 */
	private Log logger = LogFactory.getLog(BoletoServiceImpl.class);
	
	@Override
	public void generateBoleto(Order order) {
		logger.info("Gerar Boleto.");
		generateBoleto.generateBoleto(order);
	}

	/**
	 * Ajustar o gerenciador de boletos.
	 * @param generateBoleto O gerenciador de boletos.
	 */
	public void setGenerateBoleto(GenerateBoleto generateBoleto) {
		this.generateBoleto = generateBoleto;
	}

}

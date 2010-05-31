package br.com.buyFast.service.generate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.Generator;
import org.jboleto.control.PDFGenerator;

import br.com.buyFast.model.Bank;
import br.com.buyFast.model.ItemsOrder;
import br.com.buyFast.model.Order;

/**
 * Classe responsável pela geração de boleto.
 */
public class GenerateBoleto {

	private static final String SANTANDER = "Santander";
	private static final String HSBC = "HSBC";
	private static final String ITAU = "Itaú";
	private static final String BB = "Banco do Brasil";
	private static final String CAIXA = "Caixa Econômica";
	private static final String BRADESCO = "Bradesco";
	private static final String UNIBANCO = "Unibanco";
	private static final String REAL = "Banco Real";
	
	/**
	 * Responsável pelo log na aplicação.
	 */
	private Log logger = LogFactory.getLog(GenerateBoleto.class);
	
	/**
	 * O nome do arquivo do boleto.
	 */
	private String fileName;
	
	/**
	 * Método responsável por gerar boleto de pagamento.
	 * @param order O pedido ao qual será gerado o pedido.
	 */
	public void generateBoleto(Order order) {
		logger.info("Gerando boleto do pedido...");
		JBoletoBean boletoBean = new JBoletoBean();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		boletoBean.setDataProcessamento(df.format(new Date()));
		Vector<String> descricoes = new Vector<String>();
		double valorBoleto = 0.0d;
		for (ItemsOrder itemsOrder : order.getItemsOrders()) {
			descricoes.add(itemsOrder.getProduct().getName());
			valorBoleto += itemsOrder.getPrice();
		}
		descricoes.add("Produtos:");
		descricoes.add("Boleto referente a compras no site BuyFast");
		boletoBean.setDescricoes(descricoes);
		boletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE " + order.getBank().getBank());
		boletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO " + order.getBank().getBank());
		boletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
		boletoBean.setInstrucao2("APOS O VENCIMENTO, COBRAR MULTA DE  R$ 0.50 AO DIA");
		boletoBean.setInstrucao3("APOS O VENCIMENTO NÃO APLICAR DESCONTO");
		boletoBean.setInstrucao4("APOS O VENCIMENTO COBRAR R$ 1.50 DE TAXA DE OPERAÇÃO");
		boletoBean.setInstrucao5("ANTES DO VENCIMENTO APLICAR DESCONTO DE 5%");
		boletoBean.setNomeSacado(order.getCustomer().getName());
		boletoBean.setEnderecoSacado(order.getCustomer().getAddress().getStreet());
		boletoBean.setBairroSacado(order.getCustomer().getAddress().getDistrict());
		boletoBean.setCidadeSacado(order.getCustomer().getAddress().getCity());
		boletoBean.setUfSacado(order.getCustomer().getAddress().getState());
		boletoBean.setCepSacado(order.getCustomer().getAddress().getCep());
		boletoBean.setCpfSacado(order.getCustomer().getCpf());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 7);
		boletoBean.setDataVencimento(df.format(calendar.getTime()));
		boletoBean.setValorBoleto(Double.valueOf(valorBoleto).toString());
		boletoBean.setDataDocumento(df.format(new Date()));
		
		generate(boletoBean, order.getBank());
	}
	
	/**
	 * Gerar boleto.
	 * @param boletoBean O Boleto.
	 * @param bank O banco que será gerado o boleto.
	 */
	private void generate(JBoletoBean jBoletoBean, Bank bank) {
		this.fileName = "/" + jBoletoBean.getNomeSacado() + "_boleto.pdf";
		
		if (SANTANDER.equals(bank.getBank())) {
			geraSantander(jBoletoBean);
		} else if (HSBC.equals(bank.getBank())) {
			geraHSBC(jBoletoBean);
		} else if (ITAU.equals(bank.getBank())) {
			geraItau(jBoletoBean);
		} else if (BB.equals(bank.getBank())) {
			geraBB(jBoletoBean);
		} else if (CAIXA.equals(bank.getBank())) {
			geraCaixa(jBoletoBean);
		} else if (BRADESCO.equals(bank.getBank())) {
			geraBradesco(jBoletoBean);
		} else if (UNIBANCO.equals(bank.getBank())) {
			geraUnibanco(jBoletoBean);
		} else if (REAL.equals(bank.getBank())) {
			geraReal(jBoletoBean);
		}
	}
	
	/**
	 * Gera o boleto do banco Santander.
	 * @param jBoletoBean a bean para a gerenciamento do boleto.
	 */
	private void geraSantander(JBoletoBean jBoletoBean) {
		jBoletoBean.setCarteira("");
		jBoletoBean.setAgencia("2971");
		jBoletoBean.setContaCorrente("08690");
		jBoletoBean.setDvContaCorrente("1");
		jBoletoBean.setNossoNumero("0000003248437", 13);
		jBoletoBean.setNoDocumento("0324843");
		Generator generator = new PDFGenerator(jBoletoBean, JBoleto.SANTANDER);
		JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.SANTANDER);
		jBoleto.addBoleto();
		jBoleto.closeBoleto(fileName);
	}
	
	/**
	 * Gera o boleto do banco HSBC.
	 * @param jBoletoBean a bean para a gerenciamento do boleto.
	 */
	private void geraHSBC(JBoletoBean jBoletoBean) {
		jBoletoBean.setCarteira("");
		jBoletoBean.setAgencia("2971");
		jBoletoBean.setContaCorrente("08690");
		jBoletoBean.setDvContaCorrente("1");
		jBoletoBean.setNossoNumero("102008044", 9);
		jBoletoBean.setNoDocumento("102008");
		Generator generator = new PDFGenerator(jBoletoBean, JBoleto.HSBC);
		JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.HSBC);
		jBoleto.addBoleto();
		jBoleto.closeBoleto(fileName);
	}

	/**
	 * Gera o boleto do banco Itaú.
	 * @param jBoletoBean a bean para a gerenciamento do boleto.
	 */
	private void geraItau(JBoletoBean jBoletoBean) {
		jBoletoBean.setCarteira("175");
		jBoletoBean.setAgencia("2971");
		jBoletoBean.setContaCorrente("08690");
		jBoletoBean.setDvContaCorrente("1");
		jBoletoBean.setNossoNumero("34556", 8);
		jBoletoBean.setNoDocumento("34556");
		Generator generator = new PDFGenerator(jBoletoBean, JBoleto.ITAU);
		JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.ITAU);
		jBoleto.addBoleto();
		jBoleto.closeBoleto(fileName);
	}

	/**
	 * Gera o boleto do banco do Brasil.
	 * @param jBoletoBean a bean para a gerenciamento do boleto.
	 */
	private void geraBB(JBoletoBean jBoletoBean) {
		jBoletoBean.setCarteira("17");
		jBoletoBean.setAgencia("3415");
		jBoletoBean.setContaCorrente("00543004"); // completar com zeros quando necessario
		jBoletoBean.setNumConvenio("1101354");
		jBoletoBean.setNossoNumero("0005963971", 10);
		jBoletoBean.setDvContaCorrente("2");
		jBoletoBean.setDvAgencia("8");
		Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BANCO_DO_BRASIL);
		JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.BANCO_DO_BRASIL);
		jBoleto.addBoleto();
		jBoleto.closeBoleto(fileName);
	}

	/**
	 * Gera o boleto do banco Caixa Econômica.
	 * @param jBoletoBean a bean para a gerenciamento do boleto.
	 */
	private void geraCaixa(JBoletoBean jBoletoBean) {
		jBoletoBean.setCarteira("57");
		jBoletoBean.setAgencia("0155");
		jBoletoBean.setContaCorrente("13877");
		jBoletoBean.setDvContaCorrente("4");
		jBoletoBean.setCarteira("80"); // pode ser 80 ou 81 ou 82 (Confirmar com gerente)
		jBoletoBean.setCodigoOperacao("870");
		jBoletoBean.setCodigoFornecidoAgencia("00000324");
		jBoletoBean.setDvCodigoFornecidoAgencia("02");
		jBoletoBean.setNossoNumero("19525086", 8);
		jBoletoBean.setNoDocumento("987656123");
		Generator generator = new PDFGenerator(jBoletoBean, JBoleto.CAIXA_ECONOMICA);
		JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.CAIXA_ECONOMICA);
		jBoleto.addBoleto();
		jBoleto.closeBoleto(fileName);
	}

	/**
	 * Gera o boleto do banco Bradesco.
	 * @param jBoletoBean a bean para a gerenciamento do boleto.
	 */
	private void geraBradesco(JBoletoBean jBoletoBean) {
		jBoletoBean.setCarteira("06");
		jBoletoBean.setAgencia("2949");
		jBoletoBean.setDvAgencia("1");
		jBoletoBean.setNoDocumento("321546");
		jBoletoBean.setContaCorrente("0006580");
		jBoletoBean.setDvContaCorrente("3");
		jBoletoBean.setNossoNumero("003", 11);
		Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BRADESCO);
		JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.BRADESCO);
		jBoleto.addBoleto();
		jBoleto.closeBoleto(fileName);
	}

	/**
	 * Gera o boleto do banco Unibanco.
	 * @param jBoletoBean a bean para a gerenciamento do boleto.
	 */
	private void geraUnibanco(JBoletoBean jBoletoBean) {
		jBoletoBean.setCarteira("20");
		jBoletoBean.setAgencia("0123");
		jBoletoBean.setContaCorrente("100618");
		jBoletoBean.setDvContaCorrente("9");
		jBoletoBean.setNossoNumero("1803029901", 14);
		// código do cliente fornecido pelo unibanco.
		jBoletoBean.setCodCliente("2031671");
		Generator generator = new PDFGenerator(jBoletoBean, JBoleto.UNIBANCO);
		JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.UNIBANCO);
		jBoleto.addBoleto();
		jBoleto.closeBoleto(fileName);
	}

	/**
	 * Gera o boleto do banco Real.
	 * @param jBoletoBean a bean para a gerenciamento do boleto.
	 */
	private void geraReal(JBoletoBean jBoletoBean) {
		// ag. 0957
		// cc. 5003709 6
		jBoletoBean.setCarteira("57");
		jBoletoBean.setAgencia("0957");
		jBoletoBean.setContaCorrente("5003709");
		jBoletoBean.setDvContaCorrente("6");
		jBoletoBean.setNossoNumero("3020", 13);
		jBoletoBean.setNoDocumento("3020");
		Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BANCO_REAL);
		JBoleto jBoleto = new JBoleto(generator, jBoletoBean,
				JBoleto.BANCO_REAL);
		jBoleto.addBoleto();
		jBoleto.closeBoleto(fileName);
	}

}

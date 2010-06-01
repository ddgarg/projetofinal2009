package br.com.buyFast.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.car.Cart;
import br.com.buyFast.model.Bank;
import br.com.buyFast.model.Customer;
import br.com.buyFast.model.ItemsOrder;
import br.com.buyFast.model.Order;
import br.com.buyFast.model.StatusEnum;
import br.com.buyFast.service.EmailService;
import br.com.buyFast.service.Facade;
import br.com.buyFast.service.ServiceException;
import br.com.buyFast.util.FacesUtil;

/**
 * Classe responsável pelo controller do carrinho de compras.
 */
@Controller("cartController")
@Scope("session")
public class CartController implements Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Apresenta o log na aplicação.
	 */
	private static final Log logger = LogFactory.getLog(CartController.class);
	
	/**
	 * Representa a camada de serviço da aplicação.
	 */
	@Resource
	private Facade facade;
	
	/**
	 * Carrinho de compras.
	 */
	@Resource
	private Cart cart;
	
	/**
	 * Responsável pelo serviço de e-mail.
	 */
	@Resource
	private EmailService emailService;
	
	/**
	 * Representa o tipo de pagamento escolhido pelo cliente.
	 */
	private String paymentType;
	
	/**
	 * O banco escolhido pelo usuário.
	 */
	private Bank bank;
	
	/**
	 * Recebe o pedido após finalizar compra. Será apagado após gerar boleto.
	 */
	private Order orderBoleto;
	
	private int count;
	
	public CartController() {
		this.count = 0;
		this.bank = new Bank();
	}
	
	/**
	 * Finalizar pedido do cliente.
	 * @return
	 */
	public String finalizeOrder() {
		
		if (this.cart.getTotal() > 0) {
			/*
			 * Utilizado o procedimento abaixo para obter o usuário logado.
			 */
			FacesContext context = FacesContext.getCurrentInstance();
			Application app = context.getApplication();
			CustomerController customerController = 
				(CustomerController) app.evaluateExpressionGet(context, "#{customerController}", 
						CustomerController.class);
			
			Order order = new Order();
			order.setCustomer(customerController.getCustomer());
			order.setOrderDate(new Timestamp((new Date()).getTime()));
			order.setStatusEnum(StatusEnum.CHECKPAYMENT);
			order.setBank(this.bank);
			
			try {
				// Salvar pedido.
				order = facade.saveOrder(order);
			} catch (ServiceException e) {
				logger.error("Erro ao salvar pedido.", e);
				FacesUtil.mensErro("", FacesUtil.getMessage("cartControllerMessageErrorSaveOrder"));
			}
			
			order.setItemsOrders(new HashSet<ItemsOrder>());
			
			for (ItemsOrder itemsOrder : this.cart.getProducts()) {
				// Novo item de pedido com Chave composta.
				ItemsOrder newItemsOrder = new ItemsOrder(order.getId(), itemsOrder.getProduct().getId(),
						itemsOrder.getQuantity(), itemsOrder.getSubTotal());
				// Configurando pedido.
				newItemsOrder.setOrder(order);
				// Configurando Produto.
				newItemsOrder.setProduct(itemsOrder.getProduct());
				try {
					// Salvando Item de pedido.
					facade.mergeItemsOrder(newItemsOrder);
				} catch (ServiceException e) {
					logger.error("Erro ao salvar item de pedido - " + itemsOrder, e);
					FacesUtil.mensErro("", FacesUtil.getMessage("cartControllerMessageErrorSaveItemOrder") 
							+ itemsOrder);
				}
				// Adicionando a lista de item de pedido.
				order.getItemsOrders().add(newItemsOrder);
			}
			
			try {
				sendConfirmOrder(order);
			} catch (ServiceException e1) {
				logger.error("Erro ao enviar e-mail.", e1);
				FacesUtil.mensWarn("", FacesUtil.getMessage("customerControllerErrorSendEmailRegisterCustomer"));
			}
			
			try {
				facade.generateBoleto(order);
			} catch (Exception e) {
				logger.error("Erro ao gerar Boleto.", e);
				FacesUtil.mensErro("", FacesUtil.getMessage("cartControllerMessageErrorGenerateBoleto"));
			}
			
			FacesUtil.mensInfo("", FacesUtil.getMessage("cartControllerMessageFinalizedOrder"));
			
			// Limpar Carrinho.
			this.cart.cleanCart();
			
			this.orderBoleto = order;
			
			return "showBoleto";
		}
		
		// Não tendo produto, limpar carrinho.
		this.cart.cleanCart();
		
		// Retornar para a mesma página.
		return null;
	}
	
	/**
	 * Envia a mensagem de confirmação de pedido.
	 * @throws ServiceException 
	 */
	public void sendConfirmOrder(Order order) throws ServiceException {
			Customer customer = order.getCustomer();
		
			logger.info("Enviando e-mail para " + customer);
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
			
			StringBuilder builder = new StringBuilder();
			
			builder.append("<h2>Confirmação de pedido do site BuyFast:</h2><br />");
			builder.append("<h2>Dados do Pedido:</h2><br />");
			builder.append("<b>Hora do cadastro:</b> " + df.format(new Date()) + "<br />");
			builder.append("<b>Nome:</b> " + customer.getName() + "<br />");
			builder.append("<b>E-mail:</b> " + customer.getEmail() + "<br />");
			builder.append("<b>Assunto:</b> Pedido Finalizado<br />");
			builder.append("<b>Produtos:</b><br />");
			double total = 0.0d;
			for (ItemsOrder itemsOrder : order.getItemsOrders()) {
				builder.append(itemsOrder.getQuantity());
				builder.append(" - ");
				builder.append(itemsOrder.getProduct().getName());
				builder.append(" - R$ ");
				builder.append(itemsOrder.getPrice());
				builder.append("<br />");
				total += itemsOrder.getPrice();
			}
			
			builder.append("<br />>Total: R$ " + total);
			
			emailService.send(customer.getEmail(), "buyfast@buyfast.com", "Pedido Finalizado no Site BuyFast", 
					builder.toString());
		
	}
	
	/**
	 * Apresenta o boleto.
	 * @return
	 */
	public String viewBoleto() {
		// Apresentar boleto.
		try {
			showBoleto(this.orderBoleto.getCustomer());
			this.orderBoleto = new Order();
		} catch (Exception e) {
			logger.error("Erro ao exibir boleto.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("cartControllerMessageErrorShowBoleto"));
		}
		
		return "myAccount";
	}
	
	/**
	 * Exibe o boleto gerado.
	 * @throws Exception 
	 */
	private void showBoleto(Customer customer) throws Exception {
		try {
			File arq = new File("/" + customer.getName() + "_boleto.pdf");
			byte[] b = fileToByte(arq);

			HttpServletResponse response = (HttpServletResponse) FacesContext
			.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename=\""	+ arq.getName() + "\"");
			response.getOutputStream().write(b);
			response.getCharacterEncoding();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			logger.error("Erro ao exibir boleto.", e);
			throw new Exception("Erro ao exibir boleto.", e);
		}
	}
	
	/**
	 * Converte o arquivo informado para o tipo byte.
	 * @param arquivo O arquivo que será convertido.
	 * @return O bytes do arquivo informado.
	 * @throws Exception
	 */
	private static byte[] fileToByte(File arquivo) throws Exception {
		FileInputStream fis = new FileInputStream(arquivo);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int bytesRead = 0;
		while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
			baos.write(buffer, 0, bytesRead);
		}
		return baos.toByteArray();
	}
	
	/**
	 * Obter os produtos do carrinho de compras.
	 * @return Os produtos do carrinho de compras.
	 */
	public DataModel getProducts() {
		List<ItemsOrder> list = new ArrayList<ItemsOrder>(cart.getProducts());
		return new ListDataModel(list);
	}
	
	/**
	 * Adicionado o produto ao carrinho de compras.
	 * @return para a página de produtos.
	 */
	public String addToCart() {
		String id = FacesContext.getCurrentInstance()
			.getExternalContext().getRequestParameterMap().get("id");
		
		try {
			if (!id.equals("")) {
				cart.addToCart(facade.getProduct(Integer.parseInt(id)));
			}
		} catch (Exception e) {
			logger.error("Erro ao adicionar produto no carrinho.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("cartControllerMessageErrorAddToCart"));
		}
		
		return "viewCart";
	}
	
	/**
	 * Adicionado o produto ao carrinho de compras.<br />
	 * Utilizado para produtos vindos de categoria ou pesquisa.
	 */
	public boolean getAddToCartOther() {
		String id = FacesContext.getCurrentInstance()
			.getExternalContext().getRequestParameterMap().get("id");
		
		count += 1;
		
		try {
			if (id != null && count == 1) {
				cart.addToCart(facade.getProduct(Integer.parseInt(id)));
			}
		} catch (Exception e) {
			logger.error("Erro ao adicionar produto no carrinho.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("cartControllerMessageErrorAddToCart"));
		}
		
		return true;
	}
	
	/**
	 * Limpa o contador.
	 */
	public boolean getCleanCount() {
		this.count = 0;
		return true;
	}
	
	/**
	 * Remove o produto do carrinho de compras.
	 * @return para a página de carrinho de compras.
	 */
	public String removeProductCart() {
		String id = FacesContext.getCurrentInstance()
			.getExternalContext().getRequestParameterMap().get("id");
		try {
			cart.removeProductCart(facade.getProduct(Integer.parseInt(id)));
		} catch (Exception e) {
			logger.error("Erro ao remover produto no carrinho.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("cartControllerMessageErrorAddToCart"));
		}
		
		return "viewCart";
	}
	
	/**
	 * Atualiza a lista de produtos do carrinho de compras.
	 * @return para o carrinho de compras.
	 */
	public String updateItems() {
		for (ItemsOrder items : cart.getProducts()) {
			//Atualiza o subtotal.
			items.setSubTotal(cart.subTotal(items.getProduct().getPrice(), items.getProduct().getDiscount(),
					items.getQuantity()));
			//Caso usuário informe produto 0, remover produto.
			if (items.getQuantity() < 1) {
				cart.removeProductCart(items.getProduct());
			}
		}
		
		return "viewCart";
	}
	
	/**
	 * Obter o map com os bancos.
	 * @return O map com os bancos.
	 */
	public Map<String, String> getAllBanks() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (Bank bank : facade.getAllBanks()) {
				map.put(bank.getBank(), bank.getId().toString());
			}
		} catch (ServiceException e) {
			logger.error("Erro ao obter bancos.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("cartControllerMessageErrorGetBanks"));
		}
		
		return map;
	}
	
	//Getters and Setters
	/**
	 * Obter a quantidade de produtos no carrinho de compras.
	 * @return A quantidade de produtos no carrinho de compras.
	 */
	public Integer getQuantitiesOfProducts() {
		return cart.getProducts().size();
	}

	/**
	 * Obter o total do carrinho de compras.
	 * @return O total do carrinho de compras.
	 */
	public Double getTotal() {
		return cart.getTotal();
	}
	
	//Getters and Setters.
	
	/**
	 * Ajustar o modelo carrinho de compras.
	 * @param cart O modelo carrinho de compras.
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	/**
	 * Obter a camada de serviço da aplicação.
	 * @return A camada de serviço da aplicação.
	 */
	public Facade getFacade() {
		return facade;
	}

	/**
	 * Obter o tipo de pagamento.
	 * @return O tipo de pagamento
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * Ajustar o tipo de pagamento
	 * @param paymentType O tipo de pagamento
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * Obter o banco escolhido pelo usuário.
	 * @return O banco escolhido pelo usuário.
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * Ajustar o banco escolhido pelo usuário.
	 * @param bank O banco escolhido pelo usuário.
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	
}

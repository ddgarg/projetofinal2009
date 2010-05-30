package br.com.buyFast.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.model.Address;
import br.com.buyFast.model.Customer;
import br.com.buyFast.service.EmailService;
import br.com.buyFast.service.Facade;
import br.com.buyFast.service.ServiceException;
import br.com.buyFast.util.FacesUtil;

/**
 * Classe responsável pelo controller de clientes.
 */
@Controller("customerController")
@Scope("session")
public class CustomerController implements Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Apresenta o log na aplicação.
	 */
	private static final Log logger = LogFactory.getLog(CustomerController.class);
	
	/**
	 * Representa o cliente logado na aplicação.
	 */
	private Customer customer;
	
	/**
	 * Utilizado para obter o e-mail do usuário que esqueceu a senha.
	 */
	private String email;
	
	/**
	 * Responsável pelo serviço de e-mail.
	 */
	@Resource
	private EmailService emailService;
	
	/**
	 * Utilizado para verificação de senha.
	 */
	private String validationPassword;
	
	/**
	 * Utilizado para a verificação de email.
	 */
	private String validationEmail; 
	
	/**
	 * Representa a camada de serviço da aplicação.
	 */
	@Resource
	private Facade facade;
	
	/**
	 * Armazena o último acesso do usuário.
	 */
	private String originViewId;
	
	/**
	 * captura a sessão do contexto criado pelo JavaServer Faces.
	 */
	private FacesContext fc;
	
	/**
	 * Representa a sessão.
	 */
	private HttpSession session;
	
	/**
	 * Construtor padrão.
	 */
	public CustomerController() {
		resetCustomer();
		this.fc = FacesContext.getCurrentInstance();
		this.session = (HttpSession) fc.getExternalContext().getSession(false);
	}
	
	/**
	 * Método para acessar a conta do usuário.
	 * Este método verifica se o usuário está logado.
	 * @return caso usuário logado, redireciona para a página da conta do usuário.
	 * caso contrário, vai para a página para login.
	 */
	public String myAccount() {
		if (session.getAttribute("user") != null) {
			//Se estiver logado, ir para este caminho.
			return "myAccount";
		}
		//Se não estiver logado, ir para a página de login.
		return "userLogin";
	}

	/**
	 * Retorna para a tela de cadastro de cliente.
	 * @return Para a tela de cadastro de cliente.
	 */
	public String customerRegister() {
		
		return "customerForm";
	}
	
	/**
	 * Método responsável pelo login do usuário.
	 * @return caso usuário logado, ir para a página do mesmo.
	 */
	public String login() {
		//Obter o usuário cadastrado.
		Customer customer = null;
		try {
			customer = getCustomerLogin(this.customer.getEmail());
		} catch (ServiceException e) {
			String error = "Erro ao obter usuário.";
			logger.error(error, e);
			FacesUtil.mensErro("", FacesUtil.getMessage("customerControllerErrorgetCustomer"));
			return null;
		}
		
		//verificar usuário e senha.
		if (customer != null && customer.getPassword().equals(this.customer.getPassword())) {
			//Cria uma nova sessão.
			session.setAttribute("user", customer);
			
			//Seta o usuário.
			this.customer = customer;
			
			// caso a sessão msg esteja com valor, a remove
			if (session.getAttribute("msg") != null) {
				session.removeAttribute("msg");
			}
			
//			Retorna para a página do usuário.
			if (originViewId != null) {
				
				FacesUtil.redirectPage(originViewId);
				return null;
			} else {
				return "myAccount";
			}
			
		} else {
			FacesUtil.mensErro("", FacesUtil.getMessage("customerPasswordLoginInvalid"));
		}
		
		return null;
	}
	
	/**
	 * Fecha o login do usuário.
	 * @return retorna para a página.
	 */
	public String logout() {
		session.removeAttribute("user");
		
		// caso a sessão msg esteja com valor, a remove
		if (session.getAttribute("msg") != null) {
			session.removeAttribute("msg");
		}
		
		resetCustomer();
		
		return "home";
	}
	
	/**
	 * Registra um novo usuário.
	 * @return Retorna para a página de login.
	 */
	public String register() {
		
		// Verifica a confirmação do e-mail.
		if (this.customer.getId() == null && !this.validationEmail.equals(this.customer.getEmail())) {
			logger.error("Erro de confirmação de e-mail.");
			FacesUtil.mensErro("", FacesUtil.getMessage("messageValidateConfirmEmail"));
			
			//Apagando e-mail de confirmação.
			this.validationEmail = "";
			
			return null;
		}
		
		//Verifica a confirmação da senha.
		if (this.customer.getId() == null && !this.validationPassword.equals(this.customer.getPassword())) {
			logger.error("Erro de confirmação de senha.");
			FacesUtil.mensErro("", FacesUtil.getMessage("messageValidateConfirmPassword"));
			
			//Apaga a senha de confirmação.
			this.validationPassword = "";
			
			return null;
		}
		
		//Apaga as validações
		this.validationPassword = "";
		this.validationEmail = "";
		
		try {
			logger.info("Salavando cadastro do cliente...");
			if (this.customer.getId() != null) {
				facade.customerUpdate(customer);
				
				try {
					sendContact();
				} catch (ServiceException e) {
					logger.error("Erro ao enviar e-mail de confirmação de cadastro.", e);
					FacesUtil.mensWarn("", FacesUtil.getMessage("customerControllerErrorSendEmailRegisterCustomer"));
				}
				
				return "userLogged";
				
			} else {
				facade.customerRecord(customer);
			}
		} catch (ServiceException e) {
			logger.error("Erro ao salvar registro do cliente " + this.customer, e);
			FacesUtil.mensErro("", FacesUtil.getMessage("customerControllerErrorRegisterCustomer"));
			return null;
		}
		
		try {
			sendContact();
		} catch (ServiceException e) {
			logger.error("Erro ao enviar e-mail de confirmação de cadastro.", e);
			FacesUtil.mensWarn("", FacesUtil.getMessage("customerControllerErrorSendEmailRegisterCustomer"));
		}
		
		//Mensagem de confirmação de cadastro.
		FacesUtil.mensInfo("", FacesUtil.getMessage("customerControllerSuccessRegisterCustomer"));
		
		resetCustomer();
		
		return "userLogin";
		
	}
	
	/**
	 * Obter o usuário que tenha o e-mail informado pelo parâmetro.
	 * @param email o e-mail do usuário.
	 * @return o usuário cadastrado no sistema.
	 * @throws ServiceException 
	 */
	private Customer getCustomerLogin(String email) throws ServiceException {
		
		return facade.getCustomerLogin(email);
	}
	
	/**
	 * Envia a mensagem de confirmação de cadastro.
	 * @throws ServiceException 
	 */
	public void sendContact() throws ServiceException {
			logger.info("Enviando e-mail para " + this.customer);
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
			
			StringBuilder builder = new StringBuilder();
			
			if (this.customer.getId() != null) {
				builder.append("<h2>Atualização no cadastro do site BuyFast:</h2><br />");
			} else {
				builder.append("<h2>Cadastro no site BuyFast:</h2><br />");
			}
			builder.append("<b>Hora do cadastro:</b> " + df.format(new Date()) + "<br />");
			builder.append("<b>Nome:</b> " + this.customer.getName() + "<br />");
			builder.append("<b>E-mail:</b> " + this.customer.getEmail() + "<br />");
			builder.append("<b>Assunto:</b> Cadastro no site BuyFast<br />");
			builder.append("<b>Mensagem:</b>");
			builder.append("Seu cadastro foi efetuado com sucesso no site do buyFast.<br />");
			builder.append("Dados para acesso ao site:<br />");
			builder.append("Login: " + this.customer.getEmail());
			builder.append("<br />");
			builder.append("Senha: " + this.customer.getPassword());
			
			emailService.send(this.customer.getEmail(), "buyfast@buyfast.com", "Cadastro no site BuyFast", 
					builder.toString());
		
	}
	
	/**
	 * Utilizado para "resetar" customer. 
	 */
	private void resetCustomer() {
		this.customer = new Customer();
		this.customer.setAddress(new Address());
	}
	
	/**
	 * Prepara para a obtenção de senha do usuário.
	 * @return para a página de login.
	 */
	public String sendForgotPassword() {
		try {
			this.customer = facade.getCustomerLogin(this.email);
		} catch (ServiceException e1) {
			logger.error("Erro ao obter dados do usuário.", e1);
			FacesUtil.mensErro("", FacesUtil.getMessage("customerControllerErrorGetCustomerEmail"));
			return null;
		}
		
		logger.info("Enviando e-mail para " + this.customer);
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("<h2>site BuyFast:</h2><br />");
		builder.append("<b>Hora do envio:</b> " + df.format(new Date()) + "<br />");
		builder.append("<b>Dados:</b>");
		builder.append("Seu cadastro foi efetuado com sucesso no site do buyFast.<br />");
		builder.append("Dados para acesso ao site:<br />");
		builder.append("Login: " + this.customer.getEmail());
		builder.append("<br />");
		builder.append("Senha: " + this.customer.getPassword());
		
		try {
			emailService.send(this.customer.getEmail(), "buyfast@buyfast.com", "Cadastro no site BuyFast", 
					builder.toString());
		} catch (ServiceException e) {
			logger.error("Erro ao enviar e-mail de recuperação de senha.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("customerControllerErrorForgotPassword"));
			return null;
		}
		
		return "userLogin";
	}
	
	//Getters and Setters
	
	/**
	 * Obter o cliente logado na aplicação.
	 * @return O cliente logado na aplicação.
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Ajustar o cliente logado na aplicação.
	 * @param customer O cliente logado na aplicação.
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Obter o último acesso do usuário.
	 * Após obter este valor, o atributo de classe é setado como nulo.
	 * @return o último acesso do usuário.
	 */
	public String getOriginViewId() {
		String tmp = originViewId;
		originViewId = null;
		return tmp;
	}

	/**
	 * Ajustar o último acesso do usuário.
	 * @param originViewId O último acesso do usuário.
	 */
	public void setOriginViewId(String originViewId) {
		this.originViewId = originViewId;
	}

	/**
	 * Ajustar a camada de serviço da aplicação.
	 * @param facade A camada de serviço da aplicação.
	 */
	public void setFacade(Facade facade) {
		this.facade = facade;
	}

	/**
	 * Obter validação da senha.
	 * @return a senha para validação.
	 */
	public String getValidationPassword() {
		return validationPassword;
	}

	/**
	 * Ajustar a validação da senha.
	 * @param validationPassword a senha para validação.
	 */
	public void setValidationPassword(String validationPassword) {
		this.validationPassword = validationPassword;
	}

	/**
	 * Obter o e-mail para validação.
	 * @return o e-mail para validação.
	 */
	public String getValidationEmail() {
		return validationEmail;
	}

	/**
	 * Ajustar o e-mail para validação.
	 * @param validationEmail O e-mail para validação.
	 */
	public void setValidationEmail(String validationEmail) {
		this.validationEmail = validationEmail;
	}

	/**
	 * Obter o e-mail para receber senha.
	 * @return O e-mail para receber senha.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Ajustar o e-mail para receber senha.
	 * @param email O e-mail para receber senha.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Ajustar o atributo responsável pelo envio de e-mail.
	 * @param emailService A camada de serviço responsável pelo envio de e-mail.
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
}

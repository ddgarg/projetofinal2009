package br.com.buyFast.controller;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.model.Customer;
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
		this.customer = new Customer();
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
			return "userLogged";
		}
		//Se não estiver logado, ir para a página de login.
		return "userLogin";
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
			
			//Retorna para a página do usuário.
			if (originViewId != null) {
				return originViewId;
			} else {
				return "userLogged";
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
		
		return "home";
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
	 * Obter a camada de serviço da aplicação.
	 * @return A camada de serviço da aplicação.
	 */
	public Facade getFacade() {
		return facade;
	}

	/**
	 * Ajustar a camada de serviço da aplicação.
	 * @param facade A camada de serviço da aplicação.
	 */
	public void setFacade(Facade facade) {
		this.facade = facade;
	}
	
}

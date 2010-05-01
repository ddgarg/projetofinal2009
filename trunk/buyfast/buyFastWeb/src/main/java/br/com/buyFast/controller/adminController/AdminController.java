package br.com.buyFast.controller.adminController;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Employee;
import br.com.buyFast.service.Facade;
import br.com.buyFast.service.ServiceException;
import br.com.buyFast.util.FacesUtil;

/**
 * Classe que representa o controller para a área administrativa.
 */
@Controller("adminController")
@Scope("session")
public class AdminController implements Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Representa o modelo {@link Employee}.
	 */
	private Employee employee;
	/**
	 * Representa o modelo {@link Administrator}.
	 */
	private Administrator admin;
	/**
	 * Guarda o último acesso para a área administrativa.
	 */
	private String originViewId;
	/**
	 * Representa a camada de serviço da aplicação.
	 */
	@Resource
	private Facade facade;
	
	/**
	 * Instancia um novo {@link AdminController}.
	 */
	public AdminController() {
		this.employee = new Employee();
		this.admin = new Administrator();
	}
	
	/**
	 * Executa o login para a área administrativa.
	 * @return
	 */
	public String login() {
		Employee employee = null;
		try {
			/*
			 * Verificando login e senha no banco de dados.
			 * Foi utilizado employee, pois funcionário e uma super class
			 * de administrador. Será verificado se employee é uma instância de
			 * administrador, caso seja setar admin. Se não, setar employee.
			 * As permissões serão validadas na classe AdminLoginPhaseListener.
			 */
			employee = facade.checkEmployee(this.admin.getUser(), this.admin.getPassword());
		} catch (ServiceException e) {
			FacesUtil.mensWarn(
					FacesUtil.getMessage("adminControllerErrorGetLoginTitle"),
					FacesUtil.getMessage("adminControllerErrorGetLogin"));
			return null;
		}

		/*
		 * Se funcionário não for nulo e senha e usuário corretas
		 * criar a sessão e redirecionar para a página principal
		 * da área administrativa.
		 */
		if (employee != null && employee.getUser().equals(admin.getUser())
				&& employee.getPassword().equals(admin.getPassword())) {
			
			/*
			 * Verifica se o usuário é um administrador. Caso seja,
			 * setar o modelo administrator.
			 */
			if (employee instanceof Administrator) {
				this.admin = (Administrator) employee;
			} else {
				this.employee = employee;
			}
			
			//Criar a sessão
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
			
			//Criar atributo na sessão com o nome do usuário logado.
			session.setAttribute("employee", employee);
			
			if (session.getAttribute("msg") != null) {
				session.removeAttribute("msg");
			}
			
			/*
			 * Caso ele senha tentado acessar uma página diretamente,
			 * redirecionar para a página.
			 */
			if (originViewId != null) {
				FacesUtil.redirectPage(originViewId);
				return null;
			}
			
			// Apresentar uma mensagem de boas vindas (Usando PrimeFaces growl)
			FacesUtil.mensInfo(
					FacesUtil.getMessage("adminHomeMessageBeginTitle"),
					FacesUtil.getMessage("adminHomeMessageBegin"));
			
			return "adminhome";
		
		} else {
			FacesUtil.mensErro(
					"",
					FacesUtil.getMessage("adminControllerErrorLoginAndPassword"));
			return null;
		}
		
	}
	
	/**
	 * Faz logout do sistema.
	 * 
	 * @return
	 */
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Object session = externalContext.getSession(false);
		HttpSession httpSession = (HttpSession) session;
		httpSession.invalidate();
		
		return "adminlogin";
	}

	/**
	 * Obter o funcionário.
	 * @return o funcionário.
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * Obter o administrador.
	 * @return o administrador.
	 */
	public Administrator getAdmin() {
		return admin;
	}

	/**
	 * Obter o último acesso do usuário.
	 * @return o último acesso do usuário.
	 */
	public String getOriginViewId() {
		String temp = originViewId;
		originViewId = null;
		return temp;
	}

	/**
	 * Guardar o último acesso do usuário.
	 * @param originViewId o último acesso do usuário.
	 */
	public void setOriginViewId(String originViewId) {
		this.originViewId = originViewId;
	}
	
}

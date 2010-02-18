package br.com.buyFast.controller.adminController;

import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
public class AdminController {

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
		Administrator administrator = null;
		try {
			administrator = facade.checkAdministrator(this.admin.getUser(), this.admin.getPassword());
		} catch (ServiceException e) {
			FacesUtil.mensWarn(
					FacesUtil.getMessage("adminControllerErrorGetLoginTitle"),
					FacesUtil.getMessage("adminControllerErrorGetLogin"));
		}
		
		if (administrator != null && administrator.getUser().equals(admin.getUser())
				&& administrator.getPassword().equals(admin.getPassword())) {
			
			admin = administrator;
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
			
			session.setAttribute("name", admin.getName());
			
			if (session.getAttribute("msg") != null) {
				session.removeAttribute("msg");
			}
			
			if (originViewId != null) {
				FacesUtil.redirectPage(originViewId);
				return null;
			}
			
			return "adminhome";
		} else {
			FacesUtil.mensErro(
					FacesUtil.getMessage("adminControllerErrorLoginAndPasswordTitle"),
					FacesUtil.getMessage("adminControllerErrorLoginAndPassword"));
			return null;
		}
		
	}
	
	/**
	 * Faz logout do sistema.
	 * 
	 * @return
	 */
	public String logout(){
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

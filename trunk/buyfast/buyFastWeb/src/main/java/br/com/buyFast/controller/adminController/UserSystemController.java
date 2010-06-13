package br.com.buyFast.controller.adminController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Employee;
import br.com.buyFast.service.Facade;
import br.com.buyFast.service.ServiceException;
import br.com.buyFast.util.FacesUtil;

/**
 * Classe responsável pelo controle de usuário do sistema.
 */
@Controller("userSystemController")
@Scope("session")
public class UserSystemController implements Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Apresenta o log no sistema.
	 */
	private Log logger = LogFactory.getLog(UserSystemController.class);

	/**
	 * Representa a camada de serviço da aplicação.
	 */
	@Resource
	private Facade facade;
	
	/**
	 * Representa o usuário do sistema.
	 */
	private Employee employee;
	
	/**
	 * Utilizado para verificação do login.
	 */
	private String login;
	
	/**
	 * Utilizado para verificação de senha.
	 */
	private String password;
	
	/**
	 * Informa se é um usuário administrador.
	 */
	private boolean admin;
	
	/**
	 * Modelo de grid para apresentação de funcionário.
	 */
	private DataModel dataModel;
	
	/**
	 * Construtor.
	 */
	public UserSystemController() {
		this.employee = new Employee();
		this.admin = false;
		this.password = null;
	}
	
	/**
	 * Salvar funcionário do sistema.
	 * @return
	 */
	public String save() {
		
		if (!this.employee.getUser().equals(this.login)) {
			FacesUtil.mensErro("", FacesUtil.getMessage("userSystemControllerErroConfLogin"));
			
			return null;
		}
		
		if (!this.employee.getPassword().equals(this.password)) {
			FacesUtil.mensErro("", FacesUtil.getMessage("userSystemControllerErroConfPassword"));
			
			return null;
		}
		
		try {
			if (this.admin == true) {
				Administrator administrator = new Administrator();
				administrator.setName(this.employee.getName());
				administrator.setCpf(this.employee.getCpf());
				administrator.setEmail(this.employee.getEmail());
				administrator.setPassword(this.employee.getPassword());
				administrator.setSecondName(this.employee.getSecondName());
				administrator.setUser(this.employee.getUser());
				if (this.employee.getId() == null) {
					facade.saveAdmin(administrator);
				} else {
					facade.updateAdmin(administrator);
				}
			} else {
				if (this.employee.getId() == null) {
					facade.saveEmployee(this.employee);
				} else {
					facade.updateEmployee(this.employee);
				}
			}
		} catch (ServiceException e) {
			logger.error(e);
			FacesUtil.mensErro("", FacesUtil.getMessage("userSystemControllerErroSaveEmployee"));
			return null;
		}
		
		this.employee = new Employee();
		this.login = "";
		this.password = "";
		
		FacesUtil.mensInfo("", FacesUtil.getMessage("userSystemControllerMessageSuccess"));
		
		return null;
	}

	/**
	 * Editar funcionário do sistema.
	 * @return
	 */
	public String edit() {
		this.employee = (Employee) dataModel.getRowData();
		this.login = this.employee.getUser();
		this.password = this.employee.getPassword();
		
		if (this.employee instanceof Administrator) {
			this.admin = true;
		} else {
			this.admin = false;
		}
		
		return null;
	}
	
	/**
	 * Remover funcionário do sistema.
	 * @return
	 */
	public String delete() {
		Object obj = dataModel.getRowData();
		try {
			if (obj instanceof Administrator) {
				Administrator administrator = (Administrator) obj;
				facade.removeAdmin(administrator);
			} else {
				Employee employee = (Employee) obj;
				facade.removeEmployee(employee);
			}
		} catch (ServiceException e) {
			logger.error(e);
			FacesUtil.mensErro("", FacesUtil.getMessage("userSystemControllerErrorRemover"));
		}
		
		FacesUtil.mensInfo("", FacesUtil.getMessage("userSystemControllerMessageRemoveSuccess"));
		
		return null;
	}
	
	/**
	 * Obter todos os funcionários do sistema.
	 * @return Todos os funcionários do sistema.
	 */
	public DataModel getAllEmployee() {
		try {
			List<Employee> list = new ArrayList<Employee>();
			for (Employee employee : facade.getAllEmployee()) {
				if (employee instanceof Administrator) {
					// Administrador não entra na lista.
				} else {
					list.add(employee);
				}
			}
			dataModel = new ListDataModel(list);
			return dataModel;
		} catch (ServiceException e) {
			logger.error(e);
			FacesUtil.mensErro("", FacesUtil.getMessage("userSystemControllerErroSaveEmployee"));
		}
		
		dataModel = new ListDataModel();
		return dataModel;
	}
	
	/**
	 * Obter todos os administradores do sistema.
	 * @return Todos os administradores do sistema.
	 */
	public DataModel getAllAdmin() {
		try {
			dataModel = new ListDataModel(facade.getAllAdministrator());
			return dataModel;
		} catch (ServiceException e) {
			logger.error(e);
			FacesUtil.mensErro("", FacesUtil.getMessage("userSystemControllerErroSaveEmployee"));
		}
		
		dataModel = new ListDataModel();
		return dataModel;
	}
	
	/**
	 * Obter o funcionário do sistema.
	 * @return O funcionário do sistema.
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * Ajustar o funcionário do sistema.
	 * @param employee O funcionário do sistema.
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * Obter o login para verificação.
	 * @return O login para verificação.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Ajustar o login para verificação.
	 * @param login O login para verificação.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Obter a senha para verificação.
	 * @return A senha para verificação.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Ajustar a senha para verificação.
	 * @param password A senha para verificação.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obter o modelo da tabela.
	 * @return O modelo da tabela.
	 */
	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * Ajustar o modelo da tabela.
	 * @param dataModel O modelo da tabela.
	 */
	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	/**
	 * Ajustar a camada de serviço da aplicação.
	 * @param facade A camada de serviço da aplicação.
	 */
	public void setFacade(Facade facade) {
		this.facade = facade;
	}

	/**
	 * Informa se é um administrador.
	 * @return <code>true</code> caso seja administrador do sistema. <code>false</code> caso contrário.
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Ajustar se é um administrador do sistema.
	 * @param isAdmin <code>true</code> caso seja administrador do sistema. <code>false</code> caso contrário.
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}

package br.com.buyFast.controller.adminController;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Employee;


@Controller("adminController")
@Scope("session")
public class AdminController {

	private Employee employee;
	private Administrator admin;
	private String originViewId;
	
	public AdminController() {
		this.employee = new Employee();
		this.admin = new Administrator();
	}
	
	public String login() {
		 return "adminhome";
	}

	public Employee getEmployee() {
		return employee;
	}

	public Administrator getAdmin() {
		return admin;
	}

	public String getOriginViewId() {
		String temp = originViewId;
		originViewId = null;
		return temp;
	}

	public void setOriginViewId(String originViewId) {
		this.originViewId = originViewId;
	}
	
}

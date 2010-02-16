package br.com.buyFast.controller.adminController;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.integration.dao.GenericDao;
import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Employee;
import br.com.buyFast.model.Person;


@Controller("adminController")
@Scope("session")
public class AdminController {

	private Employee employee;
	private Administrator admin;
	private String originViewId;
	@Resource
	private GenericDao<Person, Integer> genericDao;
	
	public AdminController() {
		this.employee = new Employee();
		this.admin = new Administrator();
	}
	
	public String login() {
		genericDao.save(null);
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

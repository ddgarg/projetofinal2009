package projeto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("testes2")
@Scope("session")
@org.primefaces.optimus.config.annotations.Controller(name="testes2", scope=org.primefaces.optimus.config.Scope.SESSION)
public class Testes2 {

	private String firstname;
	
	private String surname;
	
	private int number;
	
	private DataModel model;

	private List<Sale> sales;

	public Testes2() {
		firstname = new String();
		surname = new String();
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Brand 1", 540));
		sales.add(new Sale("Brand 2", 325));
		sales.add(new Sale("Brand 3", 702));
		sales.add(new Sale("Brand 4", 421));
	}
	
	public String novo() {
		firstname = new String();
		surname = new String();
		return "testes2";
	}
	
	public void increment(ActionEvent actionEvent) {
		number++;
	}
	
	public void getCheckUser(ActionEvent actionEvent) {
		System.out.println("Passei");
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public List<Sale> getSales() {
		return sales;
	}

	public class Sale {
		
		private String brand;
		
		private Integer number;
		
		public Sale() {
			
		}
		
		public Sale(String brand, Integer number) {
			this.brand = brand;
			this.number = number;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}
		
		
	}
}

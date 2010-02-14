package br.com.buyFast.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private Customer customer;
	
	@Before
	public void setUp() throws Exception {
		customer = new Customer();
		customer.setName("customer");
	}

	@Test
	public void testEqualsObject() {
		Customer customer2 = new Customer();
		if (this.customer.equals(customer2)) {
			fail("O método equals apresentou true para objetos diferentes" +
			" como sendo iguais!");
		}
		
		customer2 = this.customer;
		
		if (!customer.equals(customer2)) {
			fail("O método equals apresenta false para objetos iguais!");
		}
	}

	@Test
	public void testToString() {
		if (!customer.toString().equals("customer")) {
			fail("O toString não apresenta a mesma infomação do" +
			" atributo de classe name!");
		}
	}

}

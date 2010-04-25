package br.com.buyFast.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {

	private Employee employee;
	
	@Before
	public void setUp() throws Exception {
		employee = new Employee();
		employee.setName("empregado");
	}

	@Test
	public void testEqualsObject() {
		Employee employee2 = new Employee();
		employee2.setName("employee");
		
		if (this.employee.equals(employee2)) {
			fail("O método equals apresentou true para objetos diferentes" +
			" como sendo iguais!");
		}
		
		employee2 = employee;
		
		if (!this.employee.equals(employee2)) {
			fail("O método equals apresenta false para objetos iguais!");
		}
	}

	@Test
	public void testToString() {
		if (!this.employee.toString().equals("empregado")) {
			fail("O toString não apresenta a mesma infomação do" +
			" atributo de classe name!");
		}
	}

}

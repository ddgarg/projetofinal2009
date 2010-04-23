package br.com.buyFast.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AdministratorTest {

	private Administrator administrator;
	
	@Before
	public void setUp() throws Exception {
		administrator = new Administrator();
		administrator.setName("test");
	}

	@Test
	public void testEqualsObject() {
		Administrator administrator = new Administrator();
		
		if (this.administrator.equals(administrator)) {
			fail("O método equals apresentou true para objetos diferentes" +
					" como sendo iguais!");
		}
		
		administrator = this.administrator;
		
		if (!this.administrator.equals(administrator)) {
			fail("O método equals apresenta false para objetos iguais!");
		}
	}

	@Test
	public void testToString() {
		if (!administrator.toString().equals("test")) {
			fail("O toString não apresenta a mesma infomação do" +
					" atributo de classe name!");
		}
	}

}

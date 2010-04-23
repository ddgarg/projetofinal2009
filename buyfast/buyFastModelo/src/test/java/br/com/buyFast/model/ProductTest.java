package br.com.buyFast.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	private Product product;
	
	@Before
	public void setUp() throws Exception {
		product = new Product();
		product.setName("produto");
	}

	@Test
	public void testEqualsObject() {
		Product product2 = new Product();
		
		if (this.product.equals(product2)) {
			fail("O método equals apresentou true para objetos diferentes" +
			" como sendo iguais!");
		}
		
		product2 = this.product;
		
		if (!this.product.equals(product2)) {
			fail("O método equals apresenta false para objetos iguais!");
		}
	}

	@Test
	public void testToString() {
		if (!product.toString().equals("produto")) {
			fail("O toString não apresenta a mesma infomação do" +
			" atributo de classe name!");
		}
	}

}

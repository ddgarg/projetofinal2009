package br.com.buyFast.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	private Category category;
	
	@Before
	public void setUp() throws Exception {
		category = new Category();
		category.setName("categoria");
	}

	@Test
	public void testEqualsObject() {
		Category category2 = new Category();
		category2.setName("categoria2");
		if (this.category.equals(category2)) {
			fail("O método equals apresentou true para objetos diferentes" +
				" como sendo iguais!");
		}
		
		category2 = this.category;
		
		if (!this.category.equals(category2)) {
			fail("O método equals apresenta false para objetos iguais!");
		}
	}

	@Test
	public void testToString() {
		if (!this.category.toString().equals("categoria")) {
			fail("O toString não apresenta a mesma infomação do" +
			" atributo de classe name!");
		}
	}

}

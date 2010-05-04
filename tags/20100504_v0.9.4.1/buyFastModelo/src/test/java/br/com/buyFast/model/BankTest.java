package br.com.buyFast.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {

	private Bank bank;
	
	@Before
	public void setUp() throws Exception {
		this.bank = new Bank("banco");
	}

	@Test
	public void testEqualsObject() {
		Bank bank = new Bank("banco2");
		if (bank.equals(this.bank)) {
			fail("O método equals apresentou true para objetos" +
					" diferentes como sendo iguais!");
		}
		
		bank = this.bank;
		
		if (!bank.equals(this.bank)) {
			fail("O método equals apresenta false para objetos iguais!");
		}
	}

	@Test
	public void testToString() {
		if (!bank.toString().equals("banco")) {
			fail("O toString não apresenta a mesma infomação do atributo" +
			" de classe name!");
		}
	}

}

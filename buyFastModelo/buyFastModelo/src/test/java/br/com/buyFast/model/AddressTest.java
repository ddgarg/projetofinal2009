package br.com.buyFast.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddressTest {

	private Address address1;
	
	private Address address2;
	
	@Before
	public void setUp() throws Exception {
		address1 = new Address();
		address1.setStreet("rua endereço 1");
		address2 = new Address();
		address2.setStreet("rua endereço 2");
	}

	@Test
	public void testEqualsObject() {
		if (address1.equals(address2)) {
			fail("O método equals apresentou true para objetos diferentes" +
					" como sendo iguais!");
		}
		Address addressEquals = address1;
		if (!address1.equals(addressEquals)) {
			fail("O método equals apresenta false para objetos iguais!");
		}
	}

	@Test
	public void testToString() {
		if (!address1.toString().equals("rua endereço 1")) {
			fail("O toString não apresenta a mesma infomação do atributo" +
					" de classe street!");
		}
		if (!address2.toString().equals("rua endereço 2")) {
			fail("O toString não apresenta a mesma infomação do atributo" +
					" de classe street!");
		}
	}

}

package br.com.buyFast.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Classe que representa o cliente do sistema.
 */
@Entity
public class Customer extends Person implements Serializable {
 
	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * O conjunto de pedidos deste cliente.
	 */
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY, targetEntity=Order.class)
	private Set<Order> orders;
	
	/**
	 * O endereço do cliente.
	 */
	@OneToOne
	protected Address address;
	
	/**
	 * Obter o conjunto de pedidos do cliente.
	 * @return O conjunto de pedidos do cliente.
	 */
	public Set<Order> getOrders() {
		return orders;
	}

	/**
	 * Ajusta o conjunto de pedidos do cliente.
	 * @param orders - O novo conjunto de pedidos do cliente.
	 */
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	/**
	 * Obter o endereço do cliente.
	 * @return O endereço do cliente.
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Ajusta o endereço do cliente.
	 * @param address - O novo endereço do cliente.
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

}
 

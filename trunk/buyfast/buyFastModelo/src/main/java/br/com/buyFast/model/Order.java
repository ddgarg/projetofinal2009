package br.com.buyFast.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe que representa o pedido do cliente.
 */
@Entity
@Table(name="Order_buy")
public class Order implements Serializable {
 
	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O identificador do pedido.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	/**
	 * A data do pedido.
	 */
	private Timestamp orderDate;
	 
	/**
	 * A data do pagamento.
	 */
	private Timestamp paymentDate;
	
	/**
	 * O cliente deste pedido.
	 */
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, targetEntity=Customer.class)
	private Customer customer;
	
	/**
	 * O conjunto de ítens de pedido.
	 */
	@OneToMany(mappedBy="order", fetch=FetchType.LAZY, cascade=CascadeType.ALL, targetEntity=ItemsOrder.class)
	private Set<ItemsOrder> itemsOrders;
	
	/**
	 * O status do pedido.
	 */
	@Enumerated(EnumType.STRING)
	private StatusEnum statusEnum;
	
	/**
	 * O banco financeiro para o pagamento do pedido.
	 */
	@OneToOne
	private Bank bank;

	/**
	 * Obter o identificador do pedido.
	 * @return O identificador do pedido.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Ajustar o identificador deste pedido.
	 * @param id - O novo identificador deste pedido.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Obter a data do pedido.
	 * @return A data do pedido.
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/**
	 * Ajustar a data do pedido.
	 * @param orderDate - A nova data do pedido.
	 */
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * Obter a data de pagamento.
	 * @return A data de pagamento.
	 */
	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	/**
	 * Ajustar a data de pagamento.
	 * @param paymentDate - A nova data de pagamento.
	 */
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * Obter o cliente do pedido.
	 * @return O cliente do pedido.
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Ajustar o cliente do pedido.
	 * @param customer - O novo cliente do pedido.
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Obter os ítens de pedido.
	 * @return O conjunto de ítens de pedido.
	 */
	public Set<ItemsOrder> getItemsOrders() {
		return itemsOrders;
	}

	/**
	 * Ajustar o conjunto de ítens de pedido.
	 * @param itemsOrders - O novo conjunto de ítens de pedido.
	 */
	public void setItemsOrders(Set<ItemsOrder> itemsOrders) {
		this.itemsOrders = itemsOrders;
	}

	/**
	 * Obter o status do pedido.
	 * @return O status do pedido.
	 */
	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

	/**
	 * Ajustar o status do pedido.
	 * @param statusEnum - O novo status do pedido.
	 */
	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	/**
	 * Obter o banco financeiro do pedido.
	 * @return O banco do pedido.
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * Ajustar o banco do pedido.
	 * @param bank - O novo banco financeiro do pedido.
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((orderDate == null) ? 0 : orderDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Order other = (Order) obj;
		if (id != other.id)
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return "Pedido: " + sdf.format(orderDate) + " - Cliente: " + customer;
	}
	
}
 

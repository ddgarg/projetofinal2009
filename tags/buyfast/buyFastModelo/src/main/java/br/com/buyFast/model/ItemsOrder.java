package br.com.buyFast.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * Classe que representa os ítens de pedido.
 */
@Entity
public class ItemsOrder implements Serializable {
 
	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O identificador do ítem de pedido.<br>
	 * Como representa uma classe composta, é embutida
	 * a classe {@link OrderPK}.
	 */
	@EmbeddedId
	private OrderPK id = new OrderPK();

	/**
	 * A quantidade do produto neste item.
	 */
	private int quantity;
	 
	/**
	 * O preço deste produto.
	 */
	private double price;
	 
	/**
	 * O subtotal deste item de pedido.
	 */
	@Transient
	private double subTotal;
	
	/**
	 * O pedido ao qual pertence este item de pedido.
	 */
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=Order.class)
	private Order order;
	
	/**
	 * O produto deste item de pedido.
	 */
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=Product.class)
	private Product product;

	/**
	 * Construtor padrão.
	 */
	public ItemsOrder() {
		// Construtor padrão.
	}
	
	/**
	 * Instancia um novo item de pedido.
	 * @param orderId O identificador do pedido.
	 * @param ProductId O identificador do produto.
	 * @param qtd Quantidade do pedido.
	 * @param price Preço total do pedido.
	 */
	public ItemsOrder(long orderId, long prodId, int qtd, double price) {
		this.id = new OrderPK(orderId, prodId);
		this.quantity = qtd;
		this.price = price;
	}
	
	/**
	 * Obter o identificador deste item de pedido.<br>
	 * Representa a chave primária do banco de dados.
	 * @return O identificador deste item de pedido.
	 */
	public OrderPK getId() {
		return id;
	}

	/**
	 * Ajusta o identificador do item de pedido.
	 * @param id - O novo identificador do tipo {@link OrderPK}.
	 */
	public void setId(OrderPK id) {
		this.id = id;
	}

	/**
	 * Obter a quantidade de item do pedido.
	 * @return A quantidade de item do pedido.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Ajustar a quantidade de item do pedido.
	 * @param quantity - A nova quantidade de item do pedido.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Obter o preço do item de pedido.
	 * @return O preço do item de pedido.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Ajusta o preço do item de pedido.
	 * @param price - O novo preço do item de pedido.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Obter o subtotal do item de pedido.
	 * @return O subtotal do item de pedido.
	 */
	public double getSubTotal() {
		return subTotal;
	}

	/**
	 * Ajustar o subtotal do item de pedido.
	 * @param subTotal - O novo subtotal do item de pedido.
	 */
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * Obter o pedido deste item.
	 * @return o pedido deste item.
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Ajustar o pedido deste item de pedido.
	 * @param order - O novo pedido deste item.
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * Obter o produto deste item de pedido.
	 * @return O produto deste item de pedido.
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Ajustar o produto deste item de pedido.
	 * @param product - O novo produto deste item de pedido.
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		temp = Double.doubleToLongBits(subTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ItemsOrder other = (ItemsOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(subTotal) != Double
				.doubleToLongBits(other.subTotal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item do pedido: " + this.order.getId() + ". Produto: " + this.product.getName();
	}
	
	
}
 

package br.com.buyFast.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Classe que representa a chave composta do item de pedido.
 */
@Embeddable
public class OrderPK implements Serializable {
 
	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O identificador do pedido.
	 */
	private long orderId;
	 
	/**
	 * O identificador do produto.
	 */
	private long prodId;

	/**
	 * O construtor da classe.
	 */
	public OrderPK() {
		//Construtor padrão.
	}
	
	/**
	 * O construtor da classe.
	 * @param orderId o id do pedido.
	 * @param prodId o id do produto.
	 */
	public OrderPK(long orderId, long prodId) {
		this.orderId = orderId;
		this.prodId = prodId;
	}
	
	/**
	 * Obter o identificador do pedido.
	 * @return O identificador do pedido.
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * Ajustar o identificador do pedido.
	 * @param orderId - O novo identificador do pedido.
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Obter o identificador do produto.
	 * @return O indetificador do produto.
	 */
	public long getProdId() {
		return prodId;
	}
	
	/**
	 * Ajustar o identificador do produto.
	 * @param prodId - O novo identificador do produto.
	 */
	public void setProdId(long prodId) {
		this.prodId = prodId;
	}
	
}
 

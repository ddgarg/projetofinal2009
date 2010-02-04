package br.com.buyFast.model;

/**
 * O enumerator com os status do sistema.
 */
public enum StatusEnum {
 
	/**
	 * Confirmar Pagamento.
	 */
	CHECKPAYMENT("Confirmar pagamento"),
	/**
	 * Pago.
	 */
	PAID("Pago"),
	/**
	 * Em processo.
	 */
	INPROCESS("Em processo"),
	/**
	 * Enviado.
	 */
	SHIPPED("Enviado"),
	/**
	 * Cancelado.
	 */
	CANCELED("Cancelado"),
	/**
	 * Finalizado.
	 */
	FINISHED("Finalizado");
	 
	/**
	 * O status do pedido.
	 */
	private String status;
	 
	/**
	 * Construtor.
	 * @param status - O status do pedido.
	 */
	StatusEnum(String status) {
		this.status = status;
	}
	 
	/**
	 * Obter o status do pedido.
	 * @return O status do pedido.
	 */
	public String getStatus() {
		return this.status;
	}

	@Override
	public String toString() {
		return this.status;
	}
	 
}
 

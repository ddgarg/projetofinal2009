package br.com.buyFast.car;

import java.util.Collection;
import java.util.HashMap;

import br.com.buyFast.model.ItemsOrder;
import br.com.buyFast.model.Product;

/**
 * Classe responsável pelo gerenciamento do carrinho de compras.
 */
public class Cart {

	/**
	 * Representa os ítens adicionado no carrinho de compras.
	 */
	private HashMap<Integer, ItemsOrder> items;
	
	/**
	 * Construtor.
	 */
	public Cart() {
		if (items == null) {
			items = new HashMap<Integer, ItemsOrder>();
		}
	}
	
	/**
	 * Adiciona o produto na lista de produto escolhidos pelo usuário.
	 * @param product O produto escolhido pelo usuário.
	 */
	public void addToCart(Product product) {
		//Obter produto.
		ItemsOrder itemsOrder = items.get(product.getId());
		
		//Não havendo produto, adicionado-lo ao Map.
		if (itemsOrder == null) {
			itemsOrder = new ItemsOrder();
			
			itemsOrder.setProduct(product);
			itemsOrder.setQuantity(1);
			
			itemsOrder.setSubTotal(subTotal(product.getPrice(), product.getDiscount(), itemsOrder.getQuantity()));
			
			items.put(product.getId(), itemsOrder);
		} else {
			itemsOrder.setQuantity(itemsOrder.getQuantity() + 1);
			itemsOrder.setSubTotal(subTotal(product.getPrice(), product.getDiscount(), itemsOrder.getQuantity()));
		}
	}
	
	/**
	 * Calcula o subtotal.
	 * @param price preço do produto.
	 * @param discount desconto do produto.
	 * @param qtd quantidade do produto.
	 * @return O sutotal.
	 */
	public double subTotal(Double price, Integer discount, Integer qtd) {
		if (discount == 0) {
			discount = 0;
		}
		
		return (price - (price * discount)/100) * qtd;
	}
	
	/**
	 * Remove o produto da lista de produtos do carrinho de compras.
	 * @param product O produto que será removido.
	 */
	public void removeProductCart(Product product) {
		items.remove(product.getId());
	}
	
	/**
	 * Retorna a lista de ítens de produtos adicionado ao carrinho.
	 * @return A lista de ítens de produtos adicionado ao carrinho.
	 */
	public Collection<ItemsOrder> getProducts() {
		return items.values();
	}
	
	/**
	 * Altera a quantidade do produto.
	 * @param idItem O identificador do produto.
	 * @param qtd A nova quantidade.
	 */
	public void changeQuantity(Integer idItem, Integer qtd) {
		ItemsOrder itemsOrder = items.get(idItem);
		itemsOrder.setQuantity(qtd);
	}
	
	/**
	 * Obter o total de produto do carrinho.
	 * @return O total de produto do carrinho.
	 */
	public Double getTotal() {
		double total = 0.0d;
		for (ItemsOrder itemsOrder : items.values()) {
			total += itemsOrder.getSubTotal();
		}
		
		return total;
	}
	
	/**
	 * Limpa os produtos do carrinho.
	 */
	public void cleanCart() {
		items.clear();
	}
}

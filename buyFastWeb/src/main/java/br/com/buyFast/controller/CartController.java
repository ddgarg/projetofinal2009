package br.com.buyFast.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.car.Cart;
import br.com.buyFast.integration.dao.ProductDao;
import br.com.buyFast.model.ItemsOrder;
import br.com.buyFast.util.FacesUtil;

/**
 * Classe responsável pelo controller do carrinho de compras.
 */
@Controller("cartController")
@Scope("session")
public class CartController implements Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Apresenta o log na aplicação.
	 */
	private static final Log logger = LogFactory.getLog(CartController.class);
	
	/**
	 * Carrinho de compras.
	 */
	@Resource
	private Cart cart;
	
	/**
	 * Representa o modelo de acesso a dados para produtos.
	 */
	@Resource
	private ProductDao productDao;
	
	private int count;
	
	public CartController() {
		this.count = 0;
	}
	
	/**
	 * Obter os produtos do carrinho de compras.
	 * @return Os produtos do carrinho de compras.
	 */
	public DataModel getProducts() {
		List<ItemsOrder> list = new ArrayList<ItemsOrder>(cart.getProducts());
		return new ListDataModel(list);
	}
	
	/**
	 * Adicionado o produto ao carrinho de compras.
	 * @return para a página de produtos.
	 */
	public String addToCart() {
		String id = FacesContext.getCurrentInstance()
			.getExternalContext().getRequestParameterMap().get("id");
		
		try {
			if (!id.equals("")) {
				cart.addToCart(productDao.searchById(Integer.parseInt(id)));
			}
		} catch (Exception e) {
			logger.error("Erro ao adicionar produto no carrinho.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("cartControllerMessageErrorAddToCart"));
		}
		
		return "viewCart";
	}
	
	/**
	 * Adicionado o produto ao carrinho de compras.<br />
	 * Utilizado para produtos vindos de categoria ou pesquisa.
	 */
	public boolean getAddToCartOther() {
		String id = FacesContext.getCurrentInstance()
			.getExternalContext().getRequestParameterMap().get("id");
		
		count += 1;
		
		try {
			if (id != null && count == 1) {
				cart.addToCart(productDao.searchById(Integer.parseInt(id)));
			}
		} catch (Exception e) {
			logger.error("Erro ao adicionar produto no carrinho.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("cartControllerMessageErrorAddToCart"));
		}
		
		return true;
	}
	
	/**
	 * Limpa o contador.
	 */
	public boolean getCleanCount() {
		this.count = 0;
		return true;
	}
	
	/**
	 * Remove o produto do carrinho de compras.
	 * @return para a página de carrinho de compras.
	 */
	public String removeProductCart() {
		String id = FacesContext.getCurrentInstance()
			.getExternalContext().getRequestParameterMap().get("id");
		try {
			cart.removeProductCart(productDao.searchById(Integer.parseInt(id)));
		} catch (Exception e) {
			logger.error("Erro ao remover produto no carrinho.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("cartControllerMessageErrorAddToCart"));
		}
		
		return "viewCart";
	}
	
	/**
	 * Atualiza a lista de produtos do carrinho de compras.
	 * @return para o carrinho de compras.
	 */
	public String updateItems() {
		for (ItemsOrder items : cart.getProducts()) {
			//Atualiza o subtotal.
			items.setSubTotal(cart.subTotal(items.getProduct().getPrice(), items.getProduct().getDiscount(),
					items.getQuantity()));
			//Caso usuário informe produto 0, remover produto.
			if (items.getQuantity() < 1) {
				cart.removeProductCart(items.getProduct());
			}
		}
		
		return "viewCart";
	}
	
	/**
	 * Obter a quantidade de produtos no carrinho de compras.
	 * @return A quantidade de produtos no carrinho de compras.
	 */
	public Integer getQuantitiesOfProducts() {
		return cart.getProducts().size();
	}

	/**
	 * Obter o total do carrinho de compras.
	 * @return O total do carrinho de compras.
	 */
	public Double getTotal() {
		return cart.getTotal();
	}
	
	//Getters and Setters.
	
	/**
	 * Ajustar o modelo carrinho de compras.
	 * @param cart O modelo carrinho de compras.
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	/**
	 * Ajustar o modelo de acesso a dados de Produto.
	 * @param productDao O modelo de acesso a dados de Produto.
	 */
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
}

package br.com.buyFast.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.model.Category;
import br.com.buyFast.model.Product;
import br.com.buyFast.service.Facade;
import br.com.buyFast.service.ServiceException;
import br.com.buyFast.util.FacesUtil;

/**
 * Classe que representa o controller para a página principal.
 */
@Controller("shopController")
@Scope("session")
public class ShopController implements Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fachada para a camada de serviço da aplicação.
	 */
	@Resource
	private Facade facade;
	
	/**
	 * Representa o produto.
	 */
	private Product product;
	
	public ShopController() {
		super();
	}

	/**
	 * Obter a lista de categorias na base de dados da aplicação
	 * @return a lista de categorias na base de dados da aplicação
	 */
	public List<Category> getAllCategories() {
		try {
			return facade.getCategories();
		} catch (ServiceException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("homeControllerErrogetAllCategories"));
		}
		return new ArrayList<Category>();
	}
	
	public List<Product> getAllProducts() {
		try {
			return facade.getAllProducts();
		} catch (ServiceException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("homePageGetAllProductsError"));
		}
		return new ArrayList<Product>();
	}
	
	/**
	 * Obtém o produto a partir do parâmetro passado na URL.<br />
	 * Caso parâmetro seja nulo, será apresentado a mensagem de
	 * erro.
	 * @return o produto para apresentação.
	 */
	public String productDetail() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id == null) {
			FacesUtil.mensErro("", FacesUtil.getMessage("shopControllerErrorIdProduct"));
			return "descriptionProduct";
		}
		try {
			this.product = facade.getProduct(new Integer(id));
		} catch (NumberFormatException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("shopControllerErrorFormatIdProduct"));
		} catch (ServiceException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("shopControllerErrorGetProduct"));
		}
		
		return "descriptionProduct";
	}

	/* Gettes e Settes */
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}

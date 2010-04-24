package br.com.buyFast.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

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
	 * Obter a lista de categorias na base de dados da aplicação.
	 * Só serão obtidas as categorias onde há produtos cadastrados.
	 * @return A lista de categorias na base de dados da aplicação
	 */
	public List<Category> getAllCategories() {
		try {
			List<Category> list = new ArrayList<Category>();
			for (Category category : facade.getCategories()) {
				if (!category.getProduct().isEmpty()) {
					list.add(category);
				}
			}
			return list;
		} catch (ServiceException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("homeControllerErrogetAllCategories"));
		}
		return Collections.emptyList();
	}
	
	/**
	 * Obter todos os produtos na base de dados.
	 * @return a lista de produtos da base de dados.
	 */
	public List<Product> getAllProducts() {
		try {
			return facade.getAllProducts();
		} catch (ServiceException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("homePageGetAllProductsError"));
		}
		return Collections.emptyList();
	}
	
	/**
	 * Obtém os últimos produtos cadastrados na base de dados.
	 * @return a lista com os últimos produtos cadastrados.
	 */
	public List<Product> getLatestProducts() {
		try {
			return facade.getLatestProducts(4);
		} catch (ServiceException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("homePageGetAllProductsError"));
		}
		return Collections.emptyList();
	}
	
	/**
	 * Obter os produtos em promoção na base de dados.
	 * @return A lista com os produtos em promoção
	 */
	public List<Product> getAllPromotionProducts() {
		try {
			return facade.getAllPromotionProducts();
		} catch (Exception e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("shopControllerErrorGetPromotionProducts"));
		}
		return Collections.emptyList();
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

	/**
	 * Obter os produtos da categoria selecionada. A categoria
	 * deverá ser passada pelo parâmetro da URL.
	 * @return O dataModel com os produtos da categoria.
	 */
	public DataModel getProductToCategory() {
		String cat = (String) FacesContext.getCurrentInstance().getExternalContext().
			getRequestParameterMap().get("cat");
		
		if (cat == null) {
			cat = "0";
		}
		
		try {
			return new ListDataModel(facade.getProductsToCategory(Integer.parseInt(cat)));
		} catch (NumberFormatException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("shopControllerErrorFormatIdCategory"));
		} catch (ServiceException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("shopControllerErrorGetProtuctsToCategory"));
		}
		
		return new ListDataModel();
	}
	
	/* Gettes e Settes */
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}

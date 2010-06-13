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
	
	/**
	 * Representa o produto para pesquisa.
	 */
	private Product searchProduct;
	
	/**
	 * Palavra-chave para busca de produtos.
	 */
	private String keyWord;

	/**
	 * Construtor padrão.
	 */
	public ShopController() {
		this.product = new Product();
		this.product.setCategory(new Category());
		this.searchProduct = new Product();
		this.searchProduct.setCategory(new Category());
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
	 * Obter o produto através do parâmetro passado pela URL.
	 * Utilizado este método para detalhes de produtos após uma pesquisa
	 * ou categoria.<br />
	 * @return <code>true</code> Quando produto achado. <code>false</code> caso contrário. 
	 */
	public boolean getProductDetail() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id == null) {
			return false;
		}
		try {
			this.product = facade.getProduct(new Integer(id));
		} catch (NumberFormatException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("shopControllerErrorFormatIdProduct"));
		} catch (ServiceException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("shopControllerErrorGetProduct"));
		}
		return true;
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
	
	/**
	 * Retorna para a página de pesquisa.
	 * @return
	 */
	public String search() {
		
		return "productSearch";
	}
	
	/**
	 * Obtém todos os produtos para a pesquisa.
	 * @return a lista de produtos da pesquisa.
	 */
	public DataModel getProductsSearch() {
		if (this.keyWord.equals("")) {
			return new ListDataModel(Collections.emptyList());
		}
		try {
			List<Product> list = facade.productSearch(this.keyWord);
			ListDataModel dataModel = new ListDataModel(list);
			this.keyWord = "";
			if (dataModel.getRowCount() == 0) {
				return new ListDataModel(Collections.emptyList());
			}
			return dataModel;
		} catch (ServiceException e) {
			this.keyWord = "";
			FacesUtil.mensErro("", FacesUtil.getMessage("shopControllerErrorProductsSearch"));
			return  new ListDataModel(Collections.emptyList());
		}
	}
	
	/**
	 * Pesquisa avançada de produtos.
	 * @return
	 */
	public String advanceSearchProduct() {
		return null;
	}
	
	/* Gettes and Settes */
	
	/**
	 * Obter produto.
	 * @return produto.
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Ajustar produto.
	 * @param product produto.
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Ajustar a camada de serviço da aplicação.
	 * @param facade a camada de serviço da aplicação.
	 */
	public void setFacade(Facade facade) {
		this.facade = facade;
	}

	/**
	 * Obter a palavra-chave para busca de produtos.
	 * @return a palavra-chave para busca de produtos.
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * Ajustar a palavra-chave para busca de produtos.
	 * @param keyWord a palavra-chave para busca de produtos.
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * Obter o produto para pesquisa.
	 * @return O produto para pesquisa.
	 */
	public Product getSearchProduct() {
		return searchProduct;
	}

	/**
	 * Ajustar o produto para pesquisa.
	 * @param searchProduct O produto para pesquisa.
	 */
	public void setSearchProduct(Product searchProduct) {
		this.searchProduct = searchProduct;
	}
	
}

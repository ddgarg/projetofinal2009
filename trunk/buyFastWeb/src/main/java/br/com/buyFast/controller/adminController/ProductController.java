package br.com.buyFast.controller.adminController;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.FacesEvent;
import javax.faces.model.DataModel;

import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.model.Category;
import br.com.buyFast.model.Product;
import br.com.buyFast.service.Facade;
import br.com.buyFast.service.ServiceException;
import br.com.buyFast.util.FacesUtil;

/**
 * Classe que representa o controller de Produtos.
 */
@Controller("productController")
@Scope("session")
public class ProductController implements Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa a camada de serviço da aplicação.
	 */
	@Resource
	private Facade facade;
	
	/**
	 * Representa o modelo de produto.
	 */
	private Product product;
	
	/**
	 * O modelo de dados para categorias.
	 */
	private DataModel model;
	
	public ProductController() {
		super();
	}
	
	/**
	 * Prepara para um novo registro de produtos.
	 * @return string com o caminho de cadastro de produtos.
	 */
	public String registerProduct() {
		this.product = new Product();
		
		return "registerProduct";
	}
	
	/**
	 * Responsável pelo envio da imagem de apresentação.
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {  
		System.out.println("Passei");
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
	
	/**
	 * Responsável pelo envio da imagem de apresentação.
	 * @param event
	 */
	public void handleFileUpload(FacesEvent event) {  
		System.out.println("Passei");
    }
	
	/**
	 * Obtém todas as categorias da base de dados.
	 * @return o map com as categorias da base de dados.
	 */
	public Map<String, Object> getAllCategories() {
		Map<String, Object> categories = new LinkedHashMap<String, Object>();
		
		try {
			for (Category category : facade.getCategories()) {
				categories.put(category.getName(), category.getId());
			}
		} catch (ServiceException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("productControllerErrorGetCategories"));
			return null;
		}
		
		return categories;
	}

	/* Gets and Setters */
	
	/**
	 * Obter o produto.
	 * @return O produto.
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Ajustar o produto.
	 * @param product - O produto.
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
}

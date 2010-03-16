package br.com.buyFast.controller.adminController;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.model.Product;
import br.com.buyFast.service.Facade;

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
	private Product products;
	
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
		this.products = new Product();
		
		return "registerProduct";
	}
	
	/**
	 * Responsável pelo envio da imagem de apresentação.
	 * @param event
	 */
	public void smallImageFileUpload(FileUploadEvent event) {  
  
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
}

package br.com.buyFast.controller.adminController;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.model.DataModel;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
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
	 * Representa o arquivo de apresentação do produto.
	 */
	private UploadedFile smallImageFileUpload;
	
	/**
	 * Representa o arquivo de imagem do produto.
	 */
	private UploadedFile imageFileUpload;
	
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
		this.smallImageFileUpload = null;
		this.imageFileUpload = null;
		this.product = new Product();
		this.product.setCategory(new Category());
		return "registerProduct";
	}
	
	/**
	 * Responsável pelo envio da imagem de apresentação do produto.
	 */
	public void fileUploadSmallImage(FileUploadEvent event) {
		this.smallImageFileUpload = event.getFile();
		this.product.setSmallImage(event.getFile().getFileName());
		// TODO Não está funcionando.
//		FacesUtil.mensInfo(event.getFile().getFileName(), "Upload do arquivo completo.");
    }
	
	/**
	 * Responsável pelo envio da imagem do produto.
	 */
	public void fileUploadImage(FileUploadEvent event) {
		this.imageFileUpload = event.getFile();
		this.product.setImage(event.getFile().getFileName());
		// TODO Não está funcionando.
//		FacesUtil.mensInfo(event.getFile().getFileName(), "Upload do arquivo completo.");
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

	/**
	 * Método responsável por salvar ou atualizar o produto.
	 * @return o caminho para a página.
	 */
	public String save() {
		if (smallImageFileUpload == null) {
			FacesUtil.mensErro("", "A imagem de apresentação deve ser informada.");
			return null;
		}
		
		return null;
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

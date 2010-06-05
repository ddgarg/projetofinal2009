package br.com.buyFast.controller.adminController;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	 * Apresenta o log na aplicação.
	 */
	private static final Log logger = LogFactory.getLog(ProductController.class);
	
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
	 * Este método "seta" automaticamente o objeto product com
	 * o nome do arquivo carregado.
	 */
	public void fileUploadSmallImage(FileUploadEvent event) {
		this.smallImageFileUpload = event.getFile();
		this.product.setSmallImage(event.getFile().getFileName());
		// TODO Não está funcionando.
//		FacesUtil.mensInfo(event.getFile().getFileName(), "Upload do arquivo completo.");
    }
	
	/**
	 * Responsável pelo envio da imagem do produto.
	 * Este método "seta" automaticamente o objeto product com
	 * o nome do arquivo carregado.
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
	public Map<String, String> getAllCategories() {
		Map<String, String> categories = new LinkedHashMap<String, String>();
		
		try {
			for (Category category : facade.getCategories()) {
				categories.put(category.getName(), category.getId().toString());
			}
		} catch (ServiceException e) {
			logger.error("Erro ao obter categorias.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("productControllerErrorGetCategories"));
			return null;
		}
		
		return categories;
	}
	
	/**
	 * Obter todos os produtos da base de dados para um dataModel.
	 * @return todos os produtos da base de dados para um dataModel.
	 */
	public DataModel getAllProducts() {
		try {
			this.model = new ListDataModel(facade.getAllProducts());
		} catch (ServiceException e) {
			logger.error("Erro ao obter produtos.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("productControllerErrorGetAllProducts"));
			return null;
		}
		
		return model;
	}
	
	/**
	 * Remove o produto da base de dados.
	 * @return
	 */
	public String removeProduct() {
		this.product = getSelectedProduct();
		if (product != null && product.getId() != null) {
			try {
				facade.removeProduct(product);
			} catch (ServiceException e) {
				logger.error("Erro ao remover produto.", e);
				FacesUtil.mensErro("", FacesUtil.getMessage("productControllerErrorRemoveProduct"));
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Edita o produto da base de dados.
	 * @return
	 */
	public String editProduct() {
		this.product = getSelectedProduct();
		return "registerProduct";
	}

	/**
	 * Obtém o produto selecionado na tabela.
	 * @return o produto selecionado na tabela.
	 */
	public Product getSelectedProduct() {
		return (Product) model.getRowData();
	}
	
	/**
	 * Método responsável por salvar ou atualizar o produto.
	 * @return o caminho para a página.
	 */
	public String save() {
		/*
		 * Verificando se o nome do produto já existe.
		 */
		try {
			if (this.product.getId() == null && facade.isAnExistingProduct(this.product)) {
				FacesUtil.mensWarn("", FacesUtil.getMessage("productControllerProductExists"));
				return null;
			}
		} catch (ServiceException e1) {
			logger.error("Erro ao verificar existência de produto.", e1);
			FacesUtil.mensErro("", FacesUtil.getMessage("productControllerErroVerificProduct"));
			return null;
		}
		
		if (smallImageFileUpload == null && this.product.getSmallImage() == null) {
			FacesUtil.mensErro("", FacesUtil.getMessage("productControllerRequeredSmallImage"));
			/*
			 * Neste caso a imagem de apresentação do arquivo é obrigatório.
			 * Se o usuário não informar, apresentar informação que o campo
			 * é obrigatório.
			 */
			return null;
		} else {
			/*
			 * Salvando arquivo de imagem de apresentação.
			 * Caso ocorra erro, retornar null.
			 */
			if (this.smallImageFileUpload != null) {
				if (!prepareFileImage(this.smallImageFileUpload)) {
					return null;
				}
			}
			/*
			 * Salvando arquivo de imagem.
			 * Sendo diferente de null, será feito o "carregamento"
			 * do arquivo. Caso ocorra algum erro, retornar null.
			 */
			if (this.imageFileUpload != null) {
				if (!prepareFileImage(this.imageFileUpload)) {
					return null;
				}
			}
			/*
			 * Neste momento será carregado a categoria do produto.
			 * O id da categoria já é carregado no objeto categoria do produto.
			 */
			try {
				this.product.setCategory(facade.getCategory(this.product.getCategory().getId()));
			} catch (ServiceException e1) {
				logger.error("Erro ao obte categoria do produto.", e1);
				FacesUtil.mensErro("", FacesUtil.getMessage("productControllerErrorGetCategory"));
				return null;
			}
			/*
			 * Salvando produto.
			 */
			if (this.product.getId() == null) {
				try {
					facade.saveProduct(product);
				} catch (ServiceException e) {
					logger.error("Erro ao salvar produto.", e);
					FacesUtil.mensErro("", FacesUtil.getMessage("productControllerErroOnSave"));
					return null;
				}
			} else {
				try {
					facade.updateProduct(product);
				} catch (ServiceException e) {
					logger.error("Erro ao atualizar produto.", e);
					FacesUtil.mensErro("", FacesUtil.getMessage("productControllerErroOnUpdate"));
					return null;
				}
			}
		}
		
		FacesUtil.mensInfo("", FacesUtil.getMessage("productControllerSuccessOperation"));
		return "showProducts";
	}
	
	/**
	 * Prepara e salva os arquivos de imagem em disco.
	 * @return <code>true</code> no sucesso da operação.
	 * <code>false</code> caso contrário.
	 */
	private boolean prepareFileImage(UploadedFile imageFileUpload) {
		/*
		 * Obtendo o caminho físico para a gravação do arquivo de imagem.
		 */
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		/*
		 * Devido a validação para verificação de arquivo de imagem de apresentação,
		 * não será necessário nova verificação. Também não será verificado a extensão
		 * do arquivo, pois o component que faz o upload já garante que os arquivos
		 * são do tipo *.jpg ou *.gif.
		 */
		InputStream streamSmallFile = null;
		try {
			streamSmallFile = imageFileUpload.getInputstream();
		} catch (IOException e) {
			logger.error("Erro de E/S no upload do arquivo.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("productControllerErrorInpustreamSmallImage"));
			return false;
		}
		/*
		 * Prepara buffer para salvar arquivo.
		 */
		int smallImageSize = (int) imageFileUpload.getSize();
		byte[] buffer = new byte[(int) smallImageSize];
		
		try {
			facade.fileSave(buffer, streamSmallFile, imageFileUpload.getFileName(), smallImageSize,
					servletContext.getRealPath("/thumbs"));
		} catch (ServiceException e) {
			logger.error("Erro ao transferir arquivo.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("productControllerErrorSaveFileImage"));
			return false;
		}
		
		return true;
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

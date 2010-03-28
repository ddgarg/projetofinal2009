package br.com.buyFast.controller.adminController;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.model.Category;
import br.com.buyFast.service.Facade;
import br.com.buyFast.service.ServiceException;
import br.com.buyFast.util.FacesUtil;

/**
 * Classe que representa o controller de Categorias.
 */
@Controller("categoryController")
@Scope("session")
public class CategoryController implements Serializable {

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
	 * Representa o modelo categoria.
	 */
	private Category category;
	
	/**
	 * O modelo de dados para categorias.
	 */
	private DataModel model;
	
	public CategoryController() {
		super();
	}
	
	
	/**
	 * Instancia um novo objeto category para cadastro em banco.
	 * @return o caminho para o cadastro da categoria.
	 */
	public String registerCategory() {
		this.category = new Category();
		return "adminRegisterCategory";
	}
	
	/**
	 * Método usado para salvar categoria.
	 * <br />
	 * <br />
	 * <b>Este método não verifica se o nome da 
	 * categoria é nulo ou em branco. Essa validação
	 * deverá ser feita diretamente na UI.</b>
	 * @return <code>null</code>
	 */
	public String save() {
		try {
			//Verifica a existência da categoria.
			if (facade.isAnExistingCategory(this.category)) {
				FacesUtil.mensWarn("", FacesUtil.getMessage("categoryControllerCategoryExists"));
				return null;
			}
		}catch (Exception e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("categoryControllerCategoryExistsError"));
			return null;
		}
		
		try {
			if (this.category.getId() != null) {
				facade.updateCategory(this.category);
				return "showCategory";
			} else {
				facade.saveCategory(this.category);
			}
		} catch (ServiceException e) {
			//Apresenta a mensagem de erro.
			FacesUtil.mensErro("", FacesUtil.getMessage("adminRegisterCategoryError"));
			return null;
		} finally {
			//Apresenta mensagem de sucesso na operação.
			FacesUtil.mensInfo("", FacesUtil.getMessage("SuccessInOperationMessage"));
			this.category = new Category();
		}
		
		return null;
	}

	/**
	 * Obter todas as categorias para a tabela.
	 * @return modelo com todas as tabelas.
	 */
	public DataModel getAllCategories() {
		try {
			model = new ListDataModel(this.facade.getCategories());
			return model;
		} catch (ServiceException e) {
			FacesUtil.mensErro("", FacesUtil.getMessage("categoryControllerErrorGetAllCategories"));
			model = new ListDataModel();
			return model;
		}
	}
	
	/**
	 * Obtém a categoria selecionada na tabela.
	 * @return a categoria selecionada na tabela.
	 */
	public Category getSelectedcategory() {
		return (Category) model.getRowData();
	}

	/**
	 * Remove a categoria selecionada na tabela.
	 * @return a categoria selecionada na tabela.
	 */
	public String removeSelectedCategory() {
		Category category = this.category;
		if (category != null && category.getId() != null) {
			try {
				facade.removeCategory(category);
			} catch (ServiceException e) {
				FacesUtil.mensErro("", FacesUtil.getMessage("categoryControllerErrorRemoveCategory"));
				return null;
			}
		}
		FacesUtil.mensInfo("", FacesUtil.getMessage("categoryControllerInfoRemoved"));
		
		return null;
	}
	
	/**
	 * Obter categoria.
	 * @return a categoria.
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Ajustar categoria.
	 * @param category a categoria.
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
}

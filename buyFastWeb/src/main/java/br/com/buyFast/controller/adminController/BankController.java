package br.com.buyFast.controller.adminController;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.model.Bank;
import br.com.buyFast.service.Facade;
import br.com.buyFast.service.ServiceException;
import br.com.buyFast.util.FacesUtil;

/**
 * Representa o controller para o gerenciamento de bancos.
 */
@Controller("bankController")
@Scope("session")
public class BankController implements Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Apresenta a mensagem na aplicação.
	 */
	private Log logger = LogFactory.getLog(BankController.class);

	/**
	 * Camada de serviço da aplicação.
	 */
	@Resource
	private Facade facade;
	
	/**
	 * Representa o banco.
	 */
	private Bank bank;
	
	/**
	 * Modelo de lista e bancos.
	 */
	private DataModel model;

	/**
	 * Ir para a página de gerenciamento de bancos.
	 * @return
	 */
	public String manageBank() {
		
		this.bank = new Bank();
		
		return "bankPage";
	}
	
	/**
	 * Persiste o banco na base de dados.
	 * @return
	 */
	public String save() {
		logger.info("Salvando banco...");
		try {
			if (this.bank.getId() != null) {
				facade.updateBank(this.bank);
			} else {
				facade.saveBank(this.bank);
			}
		} catch (ServiceException e) {
			logger.error("Erro ao salvar banco.");
			FacesUtil.mensErro("", FacesUtil.getMessage("adminBankMessageErrorSave"));
			
			return null;
		}
		
		this.bank = new Bank();
		
		FacesUtil.mensInfo("", FacesUtil.getMessage("adminBankMessageSuccess"));
		
		return null;
	}
	
	/**
	 * Edit um banco.
	 * @return
	 */
	public String edit() {
		this.bank = getSelectedBank();
		return null;
	}
	
	/**
	 * Obter o banco selecionado na tabela.
	 * @return O banco selecionado na tabela.
	 */
	private Bank getSelectedBank() {
		return (Bank) model.getRowData();
	}
	
	/**
	 * Remove o banco da base de dados.
	 * @return
	 */
	public String remove() {
		this.bank = getSelectedBank();
		try {
			logger.info("Removendo banco ...");
			facade.removeBank(this.bank);
		} catch (ServiceException e) {
			logger.error("Erro ao remover banco.");
			FacesUtil.mensErro("", FacesUtil.getMessage("adminBankMessageErrorRemove"));
		}
		
		this.bank = new Bank();
		
		FacesUtil.mensInfo("", FacesUtil.getMessage("adminBankMessageSuccess"));
		
		return null;
	}
	
	/**
	 * Obter a lista de bancos da base de dados.
	 * @return O conjunto de Bancos da base de dados.
	 */
	public DataModel getAllBank() {
		try {
			model = new ListDataModel(facade.getAllBanks());
			return model;
		} catch (ServiceException e) {
			logger.error("Erro ao obter lista de bancos.");
			FacesUtil.mensErro("", FacesUtil.getMessage("adminBankMessageErrorGetListBank"));
			
			model = new ListDataModel();
			return model;
		}
	}
	
	//Getters and Setters
	
	/**
	 * Obter o banco.
	 * @return O banco.
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * Ajustar o banco.
	 * @param bank O banco.
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	/**
	 * Ajustar a camada de serviço da aplicação.
	 * @param facade A camada de serviço da aplicação.
	 */
	public void setFacade(Facade facade) {
		this.facade = facade;
	}
	
}

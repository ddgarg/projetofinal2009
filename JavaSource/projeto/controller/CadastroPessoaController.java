package projeto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import projeto.model.PessoaFisica;

/**
 * Controller de cadastro de pessoa física.
 */
@org.primefaces.optimus.config.annotations.Controller(name="cadastroPessoaController")
@Controller("cadastroPessoaController")
@Scope("session")
public class CadastroPessoaController {

	private PessoaFisica pessoaFisica;
	
	private List<PessoaFisica> listPessoas = new ArrayList<PessoaFisica>();
	
	private DataModel model;
	
	/**
	 * Cadastrar uma nova pessoa física.
	 * @return a regra de navegação.
	 */
	public String NovaPessoaFisica() {
		this.pessoaFisica = new PessoaFisica();
		return "registerPerson";
	}
	
	/**
	 * Cadastrar uma nova pessoa física (<b>TESTES</b>).
	 * @return a regra de navegação.
	 */
	public String NovaPessoaFisicaTestes() {
		this.pessoaFisica = new PessoaFisica();
		return "testes";
	}
	
	/**
	 * Salva uma lista de pessoas físicas.
	 * @return a regra de navegação.
	 */
	public String salvar() {
		this.listPessoas.add(this.pessoaFisica);
		return "home";
	}
	
	public DataModel getApresentarPessoas() {
		return model = new ListDataModel(this.listPessoas);
	}
	
	public String edit() {
		PessoaFisica pessoaFisica = (PessoaFisica) model.getRowData();
		this.listPessoas.remove(pessoaFisica);
		this.pessoaFisica = pessoaFisica;
		return "registerPerson";
	}
	
	public String excluir() {
		PessoaFisica pessoaFisica = (PessoaFisica) model.getRowData();
		this.listPessoas.remove(pessoaFisica);
		return "#";
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public List<PessoaFisica> getListPessoas() {
		return listPessoas;
	}

	public void setListPessoas(List<PessoaFisica> listPessoas) {
		this.listPessoas = listPessoas;
	}
	
}

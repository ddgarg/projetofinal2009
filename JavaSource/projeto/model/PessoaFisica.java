package projeto.model;

/**
 * Modelo de pessoa física.
 */
public class PessoaFisica extends Pessoa {

	/**
	 * Cadastro de pessoa física.
	 */
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}

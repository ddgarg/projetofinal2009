package br.com.caelum.aeris.entity.enumerator;

public enum Localidade {

	SAO_PAULO("São Paulo"),
	RIO_DE_JANEIRO("Rio de Janeiro"),
	NOVA_IORQUE("Nova Iorque"),
	PARIS("Paris"),
	LONDRES("Londres"),
	MOSCOU("Moscou");
	
	private final String nome;
	
	private Localidade(final String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String toString() {
		return nome;
	}
}

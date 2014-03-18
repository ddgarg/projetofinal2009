package br.com.estudo.modelo;

/**
 * 0 = Aberta
 * 1 = Respondida
 * 2 = Enviada
 * 3 = Aprovada
 * 4 = Reprovada
 * @author danieloliveira
 */
public enum StatusPesquisa {

    ABERTA("Aberta"),
    RESPONDIDA("Respondida"),
    ENVIADA("Enviada"),
    APROVADA("Aprovada"),
    REPROVADA("Reprovada");
    
    private String nome;

    private StatusPesquisa(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}

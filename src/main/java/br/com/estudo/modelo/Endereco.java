package br.com.estudo.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Endereco {

    private String endereco;
    private int numero;
    private String cidade;
    private String estado;

    public Endereco() {
        super();
    }

    public Endereco(String endereco, int numero, String cidade, String estado) {
        super();
        this.endereco = endereco;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String enderecoCompleto() {
        return endereco + ", " + numero + ".";
    }
}

package br.com.estudo.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pesquisa {

    private Long id;
    private String nome;
    private Float latitude;
    private Float longtude;
    private Endereco endereco;
    private StatusPesquisa statusPesquisa;
    private boolean status = true;
    private String mensagemApresentacao;

    public Pesquisa() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public StatusPesquisa getStatusPesquisa() {
        return statusPesquisa;
    }

    public void setStatusPesquisa(StatusPesquisa statusPesquisa) {
        this.statusPesquisa = statusPesquisa;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMensagemApresentacao() {
        return mensagemApresentacao;
    }

    public void setMensagemApresentacao(String mensagemApresentacao) {
        this.mensagemApresentacao = mensagemApresentacao;
    }

}

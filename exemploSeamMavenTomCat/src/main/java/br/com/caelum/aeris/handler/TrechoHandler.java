package br.com.caelum.aeris.handler;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.log.Log;

import br.com.caelum.aeris.entity.Trecho;

@Name("trechoHandler")
public class TrechoHandler implements Serializable {

	private static final long serialVersionUID = 2384006743750900280L;

	@Logger
	private Log log;
	
	private Trecho trecho = new Trecho();
	
	@DataModel
	private List<Trecho> trechos;
	
	@DataModelSelection
	private Trecho trechoSelecionado;
	
	@In
	private EntityManager entityManager;
	
	public void salvar(final ActionEvent event) {
		if (this.trecho.getId() != null) {
			salvar();
		} else {
			log.info("Salvando: #0", this.trecho);
			entityManager.persist(this.trecho);
		}
		this.trecho = new Trecho();
	}

	public void salvar() {
		log.info("Salvando: #0", this.trecho);
		entityManager.merge(this.trecho);
		this.trecho = new Trecho();
	}
	
	@SuppressWarnings("unchecked")
	@Factory("trechos")
	public void popularTrechos() {
		log.info("Buscando trechos do banco de dados");
		this.trechos = this.entityManager.createNamedQuery("trechos").getResultList();
	}
	
	public void editar() {
		this.trecho = this.trechoSelecionado;
	}
	
	public String remover() {
		this.entityManager.remove(trechoSelecionado);
		return "/trechos.xhtml";
	}
	
	public Trecho getTrecho() {
		return trecho;
	}

	public List<Trecho> getTrechos() {
		return trechos;
	}

}

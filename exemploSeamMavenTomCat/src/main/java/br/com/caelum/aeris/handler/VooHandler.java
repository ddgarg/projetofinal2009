package br.com.caelum.aeris.handler;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.log.Log;

import br.com.caelum.aeris.entity.Trecho;
import br.com.caelum.aeris.entity.Voo;

@Name("vooHandler")
@Scope(ScopeType.CONVERSATION)
public class VooHandler implements Serializable {

	private static final long serialVersionUID = 4007562693132758699L;

	@Logger
	private Log log;
	
	@In
	private Conversation conversation;
	
	private Trecho trechoSelecionado;
	
	@In(required = false)
	@Out(required = false)
	private Voo voo = new Voo();
	
	@DataModelSelection
	private Voo vooSelecionado;
	
	@DataModel
	private List<Voo> voos;
	
	@In
	private EntityManager entityManager;
	
	@Begin
	public String manipulaVoos(final Trecho trecho) {
		this.trechoSelecionado = entityManager.merge(trecho);
		log.info("Trecho selecionado: #0", this.trechoSelecionado);
		log.info("EntityManager: #0", entityManager);
		log.info("Id da conversação atual: #0", conversation.getId());
		return "/voos.xhtml";
	}

	public void editar() {
		this.voo = this.vooSelecionado;
	}
	
	public String remover() {
		log.info("EntityManager: #0", entityManager);
		log.info("Removendo voo: #0", this.vooSelecionado);
		/*
		 * Temos que remover o vôo do trecho, porque o trecho está sendo
		 * gerenciado pelo entityManager. Ao remover o vôo, o JPA informa
		 * que o vôo está sendo gerenciado e peristido, não permitindo a
		 * remoção.
		 */
		this.trechoSelecionado.getVoos().remove(this.vooSelecionado);
		this.vooSelecionado.setTrecho(null);
		this.entityManager.remove(this.vooSelecionado);
		this.voo = new Voo();
		return "/voos.xhtml";
	}
	
	public String salvarVoo() {
		log.info("EntityManager: #0", entityManager);
		if (this.voo.getId() == null) {
			this.trechoSelecionado.getVoos().add(this.voo);
			this.voo.setTrecho(this.trechoSelecionado);
			log.info("Salvando: #0", this.voo);
			entityManager.persist(this.voo);
		} else {
			salvar();
		}
		this.voo = new Voo();
		return "/voos.xhtml";
	}
	
	public void salvar() {
		log.info("Atualizando: #0", this.voo);
		this.voo.setTrecho(this.trechoSelecionado);
		entityManager.merge(this.voo);
	}
	
	@Factory(value = "voos")
	public void inicioVoos() {
		log.info("Obtendo voos!", new Object());
		this.voos = this.trechoSelecionado.getVoos();
	}
	
	public Trecho getTrechoSelecionado() {
		return trechoSelecionado;
	}

	public List<Voo> getVoos() {
		return voos;
	}

	public Voo getVoo() {
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}

}

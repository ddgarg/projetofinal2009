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
	
	private Voo voo = new Voo();
	
	@DataModelSelection
	private Voo vooSelecionado;
	
	@DataModel
	private List<Voo> voos;
	
//	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	@In
	private EntityManager entityManager;
	
	@Begin
	public String manipulaVoos(final Trecho trecho) {
		this.trechoSelecionado = entityManager.merge(trecho);
		log.info("Trecho selecionado: #0", this.trechoSelecionado);
		log.info("Id da conversação atual: #0", conversation.getId());
		return "/voos.xhtml";
	}

	public void editar() {
		this.voo = this.vooSelecionado;
	}
	
	public String remover() {
		this.entityManager.remove(this.voo);
		return "/voos.xhtml";
	}
	
	public void salvarVoo() {
		if (this.voo.getId() != null) {
			salvar();
		} else {
			log.info("Salvando: #0", this.voo);
			entityManager.persist(this.voo);
		}
		this.voo = new Voo();
	}

	public void salvar() {
		log.info("Salvando: #0", this.voo);
		entityManager.merge(this.voo);
		this.voo = new Voo();
	}
	
	@Factory("voos")
	public void inicioVoos() {
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

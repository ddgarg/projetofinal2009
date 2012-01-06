package br.com.caelum.aeris.handler;

import java.util.List;

import javax.faces.event.ActionEvent;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.international.StatusMessage.Severity;

import br.com.caelum.aeris.entity.Trecho;

@Name("trechoHandler")
@Scope(ScopeType.EVENT)
public class TrechoHandler extends BaseHandler {

	private static final long serialVersionUID = 2384006743750900280L;

	private Trecho trecho = new Trecho();
	
	@DataModel
	private List<Trecho> trechos;
	
	@DataModelSelection
	private Trecho trechoSelecionado;
	
	public void salvar(final ActionEvent event) {
		if (this.trecho.getId() != null) {
			salvar();
		} else {
			events.raiseEvent("novoTrecho", this.trecho);
			log.info("Salvando: #0", this.trecho);
			entityManager.persist(this.trecho);
		}
		facesMessages.add(Severity.INFO, "#{messages.trechoHandlerSucessSaveTrechoMsg} #0", this.trecho.getId());
		this.trecho = new Trecho();
	}

	public void salvar() {
		log.info("Atualizando: #0", this.trecho);
		entityManager.merge(this.trecho);
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
		facesMessages.add(Severity.INFO, "#{messages.trechoHandlerRemoveMsg}", new Object());
		return "/trechos.xhtml";
	}
	
	public Trecho getTrecho() {
		return trecho;
	}

	public List<Trecho> getTrechos() {
		return trechos;
	}

}

package br.com.caelum.aeris.handler;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.bpm.Actor;

import br.com.caelum.aeris.entity.Passagem;
import br.com.caelum.aeris.entity.Voo;

@Name("reservaHandler")
@Scope(ScopeType.EVENT)
public class ReservaHandler extends BaseHandler {

	private static final long serialVersionUID = 3967868639630508385L;

	@In
	private Actor actor;
	
	@Out(scope= ScopeType.BUSINESS_PROCESS, required = false)
	@In(scope= ScopeType.BUSINESS_PROCESS, required = false)
	private Long passagemId;

	@Out(required = false)
	private Passagem passagem;
	
	@DataModel
	private List<Voo> voosDisponiveis;

	@DataModelSelection
	private Voo voo;

	@SuppressWarnings("unchecked")
	@Factory("voosDisponiveis")
	public void consultaVoos() {
		this.voosDisponiveis = entityManager.createNamedQuery("voos")
				.getResultList();
	}

	@CreateProcess(definition = "passagem")
	public String reservar() {
		log.info("Reservando passagem para #0", this.voo);
		this.passagem = new Passagem(voo, actor.getId());
		entityManager.persist(passagem);
		this.passagemId = passagem.getId();
//		return "/cliente.xhtml";
		/*
		 * TODO
		 * Ao redirecionar para a mesma página, está lançando exceção
		 * de transação ativa, por isso, foi criada uma página para redirecionar
		 * automaticamente para a página cliente.
		 */
		return "/confirmarReserva.xhtml";
	}

	public List<Voo> getVoosDisponiveis() {
		return voosDisponiveis;
	}

	public void setVoosDisponiveis(final List<Voo> voosDisponiveis) {
		this.voosDisponiveis = voosDisponiveis;
	}

}

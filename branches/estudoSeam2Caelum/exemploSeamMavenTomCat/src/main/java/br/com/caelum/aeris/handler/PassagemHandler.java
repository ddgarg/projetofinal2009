package br.com.caelum.aeris.handler;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;

import br.com.caelum.aeris.entity.Passagem;

@Name("passagemHandler")
@Scope(ScopeType.EVENT)
public class PassagemHandler extends BaseHandler {

	private static final long serialVersionUID = -2379589701344942615L;
	
	@In(scope = ScopeType.BUSINESS_PROCESS)
	private Long passagemId;
	
	@Out(required = false)
	private Passagem passagem;

	@StartTask
	@EndTask
	public String confirmaPagamento() {
		this.passagem = entityManager.find(Passagem.class, this.passagemId);
		return "/pagamento.xhtml";
	}
	
	public String confirmaCheckIn() {
		Passagem passagem = entityManager.getReference(Passagem.class, this.passagemId);
		entityManager.remove(passagem);
		return "/cliente.xhtml";
	}
}

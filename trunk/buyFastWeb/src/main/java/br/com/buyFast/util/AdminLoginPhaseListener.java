package br.com.buyFast.util;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ietf.jgss.MessageProp;

import br.com.buyFast.controller.adminController.AdminController;

public class AdminLoginPhaseListener implements PhaseListener {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Log logger = LogFactory.getLog(AdminLoginPhaseListener.class);

	@Override
	public void afterPhase(PhaseEvent event) {
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		String viewId = context.getViewRoot().getViewId();
		
		logger.debug("Acessando página = " + viewId);
		
		//Verifica as páginas que não tem acesso externo.
		if (viewId.equals("/admin/home.xhtml") ||
				viewId.equals("/admin/home.jsf")) {
			/*
			 * Recupera os dados que estão em sessão
			 * em adminController.
			 */
			Application app = context.getApplication();
			
			AdminController adminController = (AdminController) app.evaluateExpressionGet(
					context, 
					"#{adminController}",
					AdminController.class);
			
			//Se não tiver administrador logado.
			if (adminController.getAdmin().getId() == null) {
				logger.debug("Usuário não logado.");
				/*
				 * Armazena a página ao qual o usuário está
				 * tentando entrar em sessão através
				 * da classe AdminController.
				 */
				adminController.setOriginViewId(viewId);
				
				/*
				 * Em seguida, cria a árvore de componentes para
				 * a página login.jsf que exibirá o login e senha.
				 */
				ViewHandler viewHandler = app.getViewHandler();
				UIViewRoot viewRoot = viewHandler.createView(context, "/admin/login.jsf");
				context.setViewRoot(viewRoot);
				
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
						FacesUtil.getMessage("adminAccessDeniedTitle"),
						FacesUtil.getMessage("adminAccessDeniedMessage")));
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}

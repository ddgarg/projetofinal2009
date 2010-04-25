package br.com.buyFast.util;

import java.io.Serializable;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.buyFast.controller.CustomerController;
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
				viewId.equals("/admin/home.jsf") ||
				viewId.equals("/admin/registerCategory.jsf") ||
				viewId.equals("/admin/registerCategory.xhtml") ||
				viewId.equals("/admin/registerProduct.xhtml") ||
				viewId.equals("/admin/registerProduct.jsf") ||
				viewId.equals("/admin/showCategory.xhtml") ||
				viewId.equals("/admin/showCategory.jsf") ||
				viewId.equals("/admin/showProducts.xhtml") ||
				viewId.equals("/admin/showProducts.jsf")) {
			/*
			 * Recupera os dados que estão em sessão em adminController.
			 */
			Application app = context.getApplication();
			
			AdminController adminController = (AdminController) app.evaluateExpressionGet(
					context, "#{adminController}", AdminController.class);
			
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
//				ViewHandler viewHandler = app.getViewHandler();
//				UIViewRoot viewRoot = viewHandler.createView(context, "/admin/login.jsf");
//				context.setViewRoot(viewRoot);
				FacesUtil.redirectPage("/admin/login.jsf");
				
				FacesUtil.mensWarn(
						FacesUtil.getMessage("adminAccessDeniedTitle"), 
						FacesUtil.getMessage("adminAccessDeniedMessage"));
			}
		} else if (viewId.equals("/userLogged.xhtml") || 
				   viewId.equals("/userLogged.jsf") ||
				   viewId.equals("/myAccount.xhtml") || 
				   viewId.equals("/myAccount.jsf")) {
			/*
			 * Recupera os dados que estão em sessão em CustomerController.
			 */
			Application app = context.getApplication();
			
			CustomerController customerController = (CustomerController) app.evaluateExpressionGet(
					context, "#{customerController}", CustomerController.class);
			
			//Se não tiver usuário logado.
			if (customerController.getCustomer().getId() == null) {
				logger.debug("Usuário não logado.");
				
				/*
				 * Armazena a página ao qual o usuário está
				 * tentando entrar em sessão através
				 * da classe customerController.
				 */
				customerController.setOriginViewId(viewId);
				
				FacesUtil.redirectPage("/userLogin.jsf");
				
				FacesUtil.mensWarn(
						FacesUtil.getMessage("adminAccessDeniedTitle"), 
						FacesUtil.getMessage("userAccessDeniedMessage"));
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}

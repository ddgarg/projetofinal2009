package br.com.buyFast.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * Classe utilitário para delegar mensagens para a aplicação.<br />
 * Apresenta as mensagens na UI.
 */
public class FacesUtil {
	
	/**
	 * Apresenta uma mensagem de informação.
	 * @param message a mensagem que será apresentada.
	 */
    public static void mensInfo(String message) {
        mensagem(message, FacesMessage.SEVERITY_INFO);
    }

    /**
     * Apresenta uma mensagem de erro.
     * @param message a mensagem que será apresentada.
     */
    public static void mensErro(String message) {
        mensagem(message, FacesMessage.SEVERITY_ERROR);
    }

    /**
     * Apresenta a mensagem na UI.
     * @param message a mensagem que será apresentada.
     * @param severity o tipo de mensagem.
     * @see FacesMessage
     */
    public static void mensagem(String message, 
    		FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().
        	addMessage(null, new FacesMessage(severity, message, null));
    }
    
    public static String get(String param) {
    	
    	return (String) FacesContext.getCurrentInstance().
			getExternalContext().
			getRequestParameterMap().get(param);
    } 
    
    /**
     * Obtém as mensagens do arquivo de propriedades.
     * @param key a chave da mensagem no arquivo de propriedades.
     * @return a mensagem referente a chave.
     */
   public static final String getMessage(String key) {
	   FacesContext context = FacesContext.getCurrentInstance();
	   UIViewRoot viewRoot = context.getViewRoot();
	   ResourceBundle bundle = ResourceBundle.getBundle("ui.Messages" , viewRoot.getLocale());
	   return bundle.getString(key);
   }
    
}

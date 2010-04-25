package br.com.buyFast.util;

import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Classe responsável pela internacionalização do sistema.
 */
@Controller("localeController")
@Scope("session")
public class LocaleController {
	
	/**
	 * Localização corrente do sistema.
	 */
	private Locale currentLocale = new Locale("pt", "BR");

	/**
	 * Trocar a localização para Inglês.
	 */
	public void englishLocale() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		currentLocale = Locale.US;
		viewRoot.setLocale(currentLocale);
	}

	/**
	 * Trocar a localização para português-Brasil.
	 */
	public void portugueseLocale() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		currentLocale = new Locale("pt", "BR");
		viewRoot.setLocale(currentLocale);
	}

	/**
	 * Obter a localização corrente do sistema.
	 * @return a localização corrente do sistema.
	 */
	public Locale getCurrentLocale() {
		return currentLocale;
	}
}
package br.com.buyFast.util;

import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("localeController")
@Scope("session")
public class LocaleController {
	private Locale currentLocale = new Locale("pt", "BR");

	public void englishLocale() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		currentLocale = Locale.US;
		viewRoot.setLocale(currentLocale);
	}

	public void portugueseLocale() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		currentLocale = new Locale("pt", "BR");
		viewRoot.setLocale(currentLocale);
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}
}
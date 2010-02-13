package br.com.buyFast.util;

import java.util.Locale;

import javax.faces.context.FacesContext;

public class LocaleSessionBean {

	public static final Locale BRAZIL = new Locale("pt","BR");
	
	public String changelocale(Locale locale) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(locale);
		return "login";
	}
	
	public String languageEnglish() {
		return changelocale(Locale.ENGLISH);
	}
	
	public String languagebrazil() {
		return changelocale(BRAZIL);
	}
	
}

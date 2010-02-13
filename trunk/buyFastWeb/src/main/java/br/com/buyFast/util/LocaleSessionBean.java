package br.com.buyFast.util;

import java.util.Locale;

import javax.faces.context.FacesContext;

public class LocaleSessionBean {

	public static final Locale BRAZIL = new Locale("pt","BR");
	
	public String changeLocale(Locale locale) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(locale);
		return "";
	}
	
	public String languageEnglish() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.ENGLISH);
		return "";
	}
	
	public String languageBrazil() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(BRAZIL);
		return "";
	}
	
}

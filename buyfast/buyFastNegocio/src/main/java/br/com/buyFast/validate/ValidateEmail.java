package br.com.buyFast.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe responsável pela validação de e-mail.
 */
public class ValidateEmail {

	/**
	 * Valida o e-mail passado pelo parâmetro.
	 * @param email O e-mail que será validado.
	 * @return <code>true</code> se e-mail válido.
	 * <code>false</code> caso contrário.
	 */
	public boolean validate(String email) {
		
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+"); 
		
		Matcher m = p.matcher(email); 
		
		boolean matchFound = m.matches( ); 
		
		if (!matchFound) { 
			return false;
		} else {
			return true;
		}
	}
	
}

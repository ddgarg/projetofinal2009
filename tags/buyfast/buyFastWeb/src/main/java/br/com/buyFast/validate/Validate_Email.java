package br.com.buyFast.validate;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.buyFast.util.FacesUtil;

/**
 * Classe responsável pela validação de e-mail.
 */
public class Validate_Email implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		ValidateEmail validateEmail = new ValidateEmail();
		
		if (!validateEmail.validate((String)value)) {
			FacesMessage message = new FacesMessage( );
			
			message.setDetail(FacesUtil.getMessage("messageValidateEmail"));
			message.setSummary(FacesUtil.getMessage("messageValidateEmail"));
		
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		    
			throw new ValidatorException(message);
		}
	}

}

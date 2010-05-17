package br.com.buyFast.validate;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.buyFast.util.FacesUtil;

/**
 * Classe responsável pela validação do CPF.
 */
public class Validate_Cpf implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		ValidateCpf validateCpf = new ValidateCpf();
		
		if (!validateCpf.isCPFValid((String) value)) {
			FacesMessage message = new FacesMessage( );
			
			message.setDetail(FacesUtil.getMessage("messageValidateCPF"));
			message.setSummary(FacesUtil.getMessage("messageValidateCPF"));
		
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		    
			throw new ValidatorException(message);
		}
	}

}

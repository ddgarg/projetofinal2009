package br.com.caelum.aeris.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

/**
 * Valida se a data de chegada é posterior a data de partida.
 */
@ValidatorClass(ChegadaMaiorQuePartidaValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChegadaMaiorQuePartida {
	
	String message() default "A data de chegada deve ser posterior a data de partida";

}

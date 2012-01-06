package br.com.caelum.aeris.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

/**
 * Valida se o campo contém caracteres maiúsculos apenas.
 */
@ValidatorClass(MaiusculoValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Maiusculo {
	String message() default "{validator.maiusculo}";
}

package br.com.buyFast.controller;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Classe que representa o controller para a página de contatos.
 */
@Controller("contactController")
@Scope("session")
public class ContactController implements Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

}

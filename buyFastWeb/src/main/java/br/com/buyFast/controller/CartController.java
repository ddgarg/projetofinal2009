package br.com.buyFast.controller;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Classe responsável pelo controller do carrinho de compras.
 */
@Controller("cartController")
@Scope("session")
public class CartController implements Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

}

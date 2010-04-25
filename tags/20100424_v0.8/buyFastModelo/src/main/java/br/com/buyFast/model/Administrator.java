package br.com.buyFast.model;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Classe que representa o administrador do sistema.
 * @see Employee
 */
@Entity
public class Administrator extends Employee implements Serializable {

    /**
     * {@link Serializable}.
     */
    private static final long serialVersionUID = 1L;
}

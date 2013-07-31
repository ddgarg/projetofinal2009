package br.com.estudo.ejb;

import javax.ejb.Local;

@Local
public interface Calculadora {
    double soma( double a , double b );
}

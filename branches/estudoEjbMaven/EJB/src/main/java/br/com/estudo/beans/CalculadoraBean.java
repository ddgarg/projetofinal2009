package br.com.estudo.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

import br.com.estudo.ejb.Calculadora;

@Stateless
public class CalculadoraBean implements Calculadora {

    @PostConstruct
    public void inicializando() {
        System.out.println(" Mais uma calculadora criada ... ");
    }

    @Override
    public double soma(double a, double b) {
        return a + b;
    }

    @PreDestroy
    public void destruindo() {
        System.out.println(" Mais uma calculadora será destruída ... ");
    }

}

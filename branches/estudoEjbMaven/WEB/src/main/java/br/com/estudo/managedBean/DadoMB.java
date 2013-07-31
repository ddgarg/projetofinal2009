package br.com.estudo.managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.estudo.ejb.LancadorDeDado;

@ManagedBean
public class DadoMB {
    
    @EJB
    private LancadorDeDado lancadorDeDado;

    private int resultado;

    public void lancaDado() {
        this.resultado = this.lancadorDeDado.lanca();
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

}

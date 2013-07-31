package br.com.estudo.ejb;

import javax.ejb.Remote;

@Remote
public interface LancadorDeDado {

    int lanca();
    
}

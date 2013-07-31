package br.com.estudo.remoteTest;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.estudo.ejb.LancadorDeDado;

public class TesteCicloDeVidaSLSB {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        properties.put("jnp.socket.Factory", "org.jnp.interfaces.TimedSocketFactory");
        properties.setProperty("java.naming.provider.url", "jnp://localhost:1099");
        
        try {
            final Context ctx = new InitialContext(properties);

            for (int i = 0; i < 100; i++) {
                final LancadorDeDado lancadorDeDado = (LancadorDeDado) ctx.lookup(LancadorDeDado.class.getName());
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            System.out.println(lancadorDeDado.lanca());
                        }
                    }
                });
                thread.start();
            }
            
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
}

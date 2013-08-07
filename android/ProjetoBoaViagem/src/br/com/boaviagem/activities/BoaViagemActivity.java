package br.com.boaviagem.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import br.com.projetoboaviagem.R;

public class BoaViagemActivity extends Activity {

    private EditText usuario;
    private EditText senha;

    public void entrarOnClick(View v) {
        String usuarioInformado = usuario.getText().toString();
        
        String senhaInformada = senha.getText().toString();
        
        if ("admin".equals(usuarioInformado) && "123".equals(senhaInformada)) {
            // vai para outra activity
        } else {
            // mostra uma mensagem de erro
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
    }

}

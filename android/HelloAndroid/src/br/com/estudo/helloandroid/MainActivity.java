package br.com.estudo.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText nomeEditText;
    private TextView saudacaoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomeEditText = (EditText) findViewById(R.id.nomeEditText);
        saudacaoTextView = (TextView) findViewById(R.id.saudacaoText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void surpreenderUsuario(View v) {
        String saudacao = getResources().getString(R.string.saudacao);
        Editable text = nomeEditText.getText();
        saudacaoTextView.setText(String.format("%s %s.", saudacao, text));
        nomeEditText.setText("");

    }

    public void novaJanela(View v) {
        Intent intent = new Intent(SaudacaoActivity.ACAO_EXIBIR_SAUDACAO);

        intent.addCategory(SaudacaoActivity.CATEGORIA_SAUDACAO);

        String texto = nomeEditText.getText().toString();

        intent.putExtra(SaudacaoActivity.EXTRA_NOME_USUARIO, texto);

        startActivity(intent);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Start!");
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Resume!");
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Pause!");
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Stop!");
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Destroy!");
    }
}

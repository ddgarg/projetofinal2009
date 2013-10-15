package com.example.consultalivrosrest;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class LivrosSearchActivity extends Activity {

	private ListView lista;
	private EditText texto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livros_search);

		lista = (ListView) findViewById(R.id.lista);
		texto = (EditText) findViewById(R.id.texto);

	}

	public void buscar(View v) {
		String filtro = texto.getText().toString();
		new LivrosTask().execute(filtro);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.livros_search, menu);
		return true;
	}

	private class LivrosTask extends AsyncTask<String, Void, String[]> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(LivrosSearchActivity.this);
			dialog.setTitle("Processando...");
			dialog.setMessage("Por favor, aguarde!");
			dialog.setCancelable(false);
			dialog.setIndeterminate(true);
			dialog.show();
		}

		@Override
		protected void onPostExecute(String[] result) {
			if (result != null) {
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						getBaseContext(), android.R.layout.simple_list_item_1,
						result);
				lista.setAdapter(adapter);
			}
			dialog.dismiss();
		}

		@Override
		protected String[] doInBackground(String... params) {
			try {
				String filtro = params[0];
				if (TextUtils.isEmpty(filtro)) {
					return null;
				}
				String urlLivrosApi = "https://www.googleapis.com/books/v1/volumes?q=";
				String url = Uri.parse(urlLivrosApi + filtro).toString();
				String conteudo = HTTPUtils.acessar(url);

				// pegamos o resultado
				JSONObject jsonObject = new JSONObject(conteudo);
				JSONArray resultados = jsonObject.getJSONArray("items");
				// montamos o resultado
				String[] livros = new String[resultados.length()];
				for (int i = 0; i < resultados.length(); i++) {
					String value = new String();
					JSONObject livro = resultados.getJSONObject(i);
					JSONObject volumeInfo = livro.getJSONObject("volumeInfo");
					
					value = volumeInfo.getString("title") + "\nAutore(s):\n";
					
					JSONArray autores = volumeInfo.getJSONArray("authors");
					for (int x = 0; x < autores.length(); x++) {
						value += autores.get(x);
					}
					
					value += "\nDescrição:\n" + volumeInfo.getString("description");
					
					livros[i] = value;
				}
				return livros;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

}

package com.projetoboaviagem.list;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

import com.projetoboaviagem.GastoActivity;
import com.projetoboaviagem.R;
import com.projetoboaviagem.ViagemActivity;
import com.projetoboaviagem.dao.BoaViagemDAO;
import com.projetoboaviagem.dao.DatabaseHelper;
import com.projetoboaviagem.enumeradores.TipoViagem;
import com.projetoboaviagem.util.Constantes;
import com.projetoboaviagem.util.GlobalUtil;

public class ViagemListActivity extends ListActivity implements OnItemClickListener, OnClickListener, ViewBinder {

    private List<Map<String, Object>> viagens;

    private AlertDialog alertDialog;

    private AlertDialog dialogConfirmacao;

    private int viagemSelecionada;

    private DatabaseHelper helper;

    private Double valorLimite;

    private BoaViagemDAO dao = new BoaViagemDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new DatabaseHelper(this);

        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);

        String valor = preferencias.getString("valor_limite", "-1");

        valorLimite = Double.valueOf(valor);

        String[] de = { "imagem", "destino", "data", "total", "barraProgresso" };

        int[] para = { R.id.tipoViagem, R.id.destino, R.id.data, R.id.valor, R.id.barraProgresso };

        SimpleAdapter adapter = new SimpleAdapter(this, listarViagens(), R.layout.lista_viagem, de, para);

        adapter.setViewBinder(this);

        setListAdapter(adapter);

        getListView().setOnItemClickListener(this);

        this.alertDialog = criaAlertDialog();
        this.dialogConfirmacao = criaDialogConfirmacao();
    }

/*    private List<Viagem> listarViagens() {
        Cursor cursor = dao.getDb().query(DatabaseHelper.Viagem.TABELA, DatabaseHelper.Viagem.COLUNAS, null, null, null, null, null);

        List<Viagem> viagens = new ArrayList<Viagem>();

        while (cursor.moveToNext()) {
            Viagem viagem = criarViagem(cursor);
            viagens.add(viagem);
        }

        cursor.close();

        return viagens;
    }*/

/*    private Viagem criarViagem(Cursor cursor) {
        return null;
    }*/
    
    private List<Map<String, Object>> listarViagens() {

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT _id, tipo_viagem, destino, data_chegada, data_saida, orcamento FROM viagem", null);

        // OUTRA CONSULTA
        /*
         * String tabela = "viagem";
         * String[] colunas = new String[]{ "_id", "tipo_viagem", "destino", "data_chegada", "data_saida", "orcamento"
         * };
         * String selecao = null;
         * String[] selecaoArgs = null;
         * String groupBy = null;
         * String having = null;
         * String orderBy = null;
         * Cursor cursor = db.query(tabela, colunas, selecao, selecaoArgs, groupBy, having, orderBy);
         */

        // OUTRA CONSULTA
        /*
         * SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
         * builder.setTables("viagem");
         * Cursor cursor = builder.query(db, colunas, selecao,
         * selecaoArgs, groupBy,
         * having, orderBy);
         */

        // move o cursor para o primeiro registro
        cursor.moveToFirst();
        // retorna a quantidade de linhas
        // cursor.getCount();
        // avança para o próximo registro
        // cursor.moveToNext();
        // fecha o cursor
        // cursor.close();

        viagens = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < cursor.getCount(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();

            String id = cursor.getString(0);
            int tipoViagem = cursor.getInt(1);
            String destino = cursor.getString(2);
            long dataChegada = cursor.getLong(3);
            long dataSaida = cursor.getLong(4);
            double orcamento = cursor.getDouble(5);

            item.put("id", id);

            if (tipoViagem == TipoViagem.VIAGEM_LAZER.ordinal()) {
                item.put("imagem", R.drawable.beachchairicon);
            } else {
                item.put("imagem", R.drawable.unemployedicon48x48);
            }

            item.put("destino", destino);

            Date dataChegadaDate = new Date(dataChegada);
            Date dataSaidaDate = new Date(dataSaida);

            String periodo = GlobalUtil.getInstance().formatarDataMedio(dataChegadaDate) + " a " + GlobalUtil.getInstance().formatarDataMedio(dataSaidaDate);
            item.put("data", periodo);

            double totalGasto = calcularTotalGasto(db, id);
            item.put("total", "Gasto total R$ " + totalGasto);

            double alerta = orcamento * valorLimite / 100;

            Double[] valores = new Double[]{ orcamento, alerta, totalGasto };

            item.put("barraProgresso", valores);

            viagens.add(item);

            cursor.moveToNext();
        }
        cursor.close();

        /*
         * viagens = new ArrayList<Map<String, Object>>();
         * Map<String, Object> item = new HashMap<String, Object>();
         * item.put("imagem", R.drawable.unemployedicon48x48);
         * item.put("destino", "São Paulo");
         * item.put("data", "02/02/2012 a 04/02/2012");
         * item.put("total", "Gasto total R$ 314,98");
         * item.put("barraProgresso", new Double[]{ 500.0, 450.0, 314.98 });
         * viagens.add(item);
         * item = new HashMap<String, Object>();
         * item.put("imagem", R.drawable.beachchairicon);
         * item.put("destino", "Maceió");
         * item.put("data", "14/05/2012 a 22/05/2012");
         * item.put("total", "Gasto total R$ 234,67");
         * item.put("barraProgresso", new Double[]{ 500.0, 300.0, 234.67 });
         * viagens.add(item);
         * item = new HashMap<String, Object>();
         * item.put("imagem", R.drawable.beachchairicon);
         * item.put("destino", "Rio de Janeiro");
         * item.put("data", "16/06/2012 a 22/06/2012");
         * item.put("total", "Gasto total R$ 490,67");
         * item.put("barraProgresso", new Double[]{ 500.0, 495.0, 490.67 });
         * viagens.add(item);
         * item = new HashMap<String, Object>();
         * item.put("imagem", R.drawable.unemployedicon48x48);
         * item.put("destino", "Rio de Janeiro");
         * item.put("data", "17/07/2012 a 27/07/2012");
         * item.put("total", "Gasto total R$ 150,67");
         * item.put("barraProgresso", new Double[]{ 500.0, 450.0, 150.67 });
         * viagens.add(item);
         * item = new HashMap<String, Object>();
         * item.put("imagem", R.drawable.unemployedicon48x48);
         * item.put("destino", "Bahia");
         * item.put("data", "18/10/2012 a 24/10/2012");
         * item.put("total", "Gasto total R$ 347,67");
         * item.put("barraProgresso", new Double[]{ 500.0, 450.0, 347.67 });
         * viagens.add(item);
         */

        return viagens;
    }

    private double calcularTotalGasto(SQLiteDatabase db, String id) {
        Cursor cursor = db.rawQuery("SELECT SUM(valor) FROM gasto WHERE viagem_id = ?", new String[]{ id });
        cursor.moveToFirst();
        double total = cursor.getDouble(0);
        cursor.close();
        return total;
    }

    @Override
    public boolean setViewValue(View view, Object data, String textRepresentation) {
        if (view.getId() == R.id.barraProgresso) {
            Double valores[] = (Double[]) data;
            ProgressBar progressBar = (ProgressBar) view;
            progressBar.setMax(valores[0].intValue());
            progressBar.setSecondaryProgress(valores[1].intValue());
            progressBar.setProgress(valores[2].intValue());
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.viagemSelecionada = position;
        alertDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int item) {
        Intent intent;
        String id = (String) viagens.get(viagemSelecionada).get(Constantes.VIAGEM_ID);

        switch (item) {
            case 0:
                intent = new Intent(this, ViagemActivity.class);
                intent.putExtra(Constantes.VIAGEM_ID, id);
                startActivity(intent);
                finish();
                break;
            case 1:
                startActivity(new Intent(this, GastoActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, GastoListActivity.class));
                break;
            case 3:
                dialogConfirmacao.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                viagens.remove(viagemSelecionada);
                removerViagem(id);
                getListView().invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                dialogConfirmacao.dismiss();
                break;
        }
    }

    private void removerViagem(String id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String where[] = new String[]{ id };
        db.delete("gasto", "viagem_id = ?", where);
        db.delete("viagem", "_id = ?", where);
    }

    private AlertDialog criaAlertDialog() {
        final CharSequence[] items = { getString(R.string.alert_editar), getString(R.string.alert_NovoGasto), getString(R.string.alert_gastos_realizados),
                getString(R.string.alert_remover) };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.alert_opcoes);
        builder.setItems(items, this);

        return builder.create();
    }

    private AlertDialog criaDialogConfirmacao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.confirmacao_exclusao_viagem);
        builder.setPositiveButton(getString(R.string.sim), this);
        builder.setNegativeButton(getString(R.string.nao), this);

        return builder.create();
    }

}

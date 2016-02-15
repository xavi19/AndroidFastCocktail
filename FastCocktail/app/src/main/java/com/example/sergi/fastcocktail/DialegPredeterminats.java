package com.example.sergi.fastcocktail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DialegPredeterminats extends AppCompatActivity {

    private ListView llista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialeg_predeterminats);
        setTitle("Combinats predeterminats");
        llista = (ListView)findViewById(R.id.listCombPred);
        llista.setAdapter(new ListAdapterCombinatsPred(MainActivity.combinats_pred.getLlista()));
        llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtId = (TextView) view.findViewById(R.id.combinat_pred_id);
                MainActivity.comanda.afegirCombinat(MainActivity.combinats_pred.trobaCombinat(Integer.parseInt(txtId.getText().toString())));
                //Toast.makeText(getApplicationContext(), txtId.getText().toString(), Toast.LENGTH_LONG).show();
                //onBackPressed();
            }
        });
    }

}

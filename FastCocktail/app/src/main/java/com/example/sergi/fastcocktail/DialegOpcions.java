package com.example.sergi.fastcocktail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DialegOpcions extends AppCompatActivity {

    private Button btnMes;
    private Button btnMenys;
    private Button btnEsb;
    private Button btnOki;
    private Button btnAcept;
    private Button btnOk;
    private Button btnAfegirPred;
    private EditText quant;
    private TextView txt_quantitat;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialeg_opcions);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("codi");
        final Combinat combinat_actual = MainActivity.comanda.trobaCombinat(id);
        txt_quantitat = (TextView)findViewById(R.id.txtQuantitat);
        txt_quantitat.setText(combinat_actual.getQuantitat()+"");
        //quant = (EditText)findViewById(R.id.editQuant);
        btnMes = (Button)findViewById(R.id.Mes);
        btnMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combinat_actual.setQuantitat(combinat_actual.getQuantitat()+1);
                txt_quantitat.setText(combinat_actual.getQuantitat() + "");
            }
        });
        btnMenys = (Button)findViewById(R.id.Menys);
        btnMenys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combinat_actual.setQuantitat(combinat_actual.getQuantitat() - 1);
                txt_quantitat.setText(combinat_actual.getQuantitat() + "");
            }
        });
        btnEsb = (Button)findViewById(R.id.btnEsb);
        btnEsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.comanda.esborrarCombinat(id);
                onBackPressed();
            }
        });
        btnAcept = (Button)findViewById(R.id.btnAccept);
        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(quant.getText().toString().trim().equals("")){
                    onBackPressed();
                }
                else{
                    if(Integer.parseInt(quant.getText().toString())<100){
                        //combinat_actual.setQuantitat(Integer.parseInt(quant.getText().toString()));
                        onBackPressed();
                    }
                    else{
                        Toast.makeText(DialegOpcions.this,"Max 99!", Toast.LENGTH_SHORT).show();
                    }

                }*/
                onBackPressed();
            }
        });
        btnAfegirPred = (Button)findViewById(R.id.btnAfegirPred);
        btnAfegirPred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.combinats_pred.afegirCombinat(combinat_actual);
                onBackPressed();
            }
        });
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        View view = getWindow().getDecorView();
        WindowManager.LayoutParams lp = (WindowManager.LayoutParams) view.getLayoutParams();
        //lp.gravity = Gravity.LEFT | Gravity.TOP;
        lp.gravity = TabLayout.GRAVITY_CENTER;
        //lp.x = 10;
        //lp.y = 10;
        lp.width = 500;
        lp.height = 470;
        getWindowManager().updateViewLayout(view, lp);
    }

}

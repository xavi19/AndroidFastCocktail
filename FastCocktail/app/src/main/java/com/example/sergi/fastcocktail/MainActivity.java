package com.example.sergi.fastcocktail;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    private LinkedList<Article> llistaArticles;
    private GridView gridFamilies;
    private GridView gridRefrescos;
    private GridView gridArticles;
    private ListView listCombinat;
    private ListView listComanda;
    private BD bd;
    static Comanda comanda;
    private Combinat combinat;
    private Button btnOK;
    private Button btnCan;
    private Button btnCobrar;
    private Button btnElimComanda;
    static Comanda combinats_pred;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comb);
        /*Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));*/
        bd = new BD();
        btnCan = (Button) findViewById(R.id.botoCan);
        btnOK = (Button) findViewById(R.id.botoAcc);
        btnCobrar = (Button)findViewById(R.id.btnCobrar);
        btnElimComanda = (Button)findViewById(R.id.btnElimComanda);
        gridFamilies = (GridView) findViewById(R.id.gridFamilies);
        gridRefrescos = (GridView) findViewById(R.id.gridRefrescos);
        gridArticles = (GridView) findViewById(R.id.gridArticles);
        llistaArticles = new LinkedList<Article>();
        listCombinat = (ListView) findViewById(R.id.llistaCombinat);
        listComanda = (ListView) findViewById(R.id.llistaComanda);
        comanda = new Comanda();
        combinat = new Combinat();
        combinats_pred = new Comanda();

        LinkedList<String> llistaGridView = new LinkedList<>();
        try {
            carregarArticles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        carregarFamilies();
        carregarRefrescos();
        omplirLlistaCombinat();
        omplirLlistaComanda();
        gridFamilies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //OMPLIM LA LLISTA DE ARTICLES
                String familia = ((TextView) view.findViewById(R.id.txtFamilia)).getText().toString().toLowerCase();
                //Toast.makeText(getApplicationContext(), familia, Toast.LENGTH_LONG).show();
                if(familia.equals("combinats predeterminats")){
                    Intent i = new Intent(MainActivity.this, DialegPredeterminats.class);
                    startActivity(i);
                }
                else{
                    omplirGridArt(familia);
                }

            }
        });
        gridArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*TextView text = (TextView) view.findViewById(R.id.txtNomArt);
                String nom = text.getText().toString().toUpperCase();*/
                TextView txtCodi = (TextView) view.findViewById(R.id.txtIdArt);
                int codi = Integer.parseInt(txtCodi.getText().toString());
                combinat.afegirArticle(bd.getArticle(codi));
                omplirLlistaCombinat();
            }
        });
        gridRefrescos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtCodi = (TextView) view.findViewById(R.id.txtIdArt);
                int codi = Integer.parseInt(txtCodi.getText().toString());
                combinat.afegirArticle(bd.getArticle(codi));
                omplirLlistaCombinat();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (combinat.getLlista().size() > 0) {
                    Combinat combinat2 = new Combinat();
                    combinat2.setLlista(combinat.getLlista());
                    comanda.afegirCombinat(combinat2);
                    omplirLlistaComanda();
                    combinat.getLlista().clear();
                    omplirLlistaCombinat();
                }
            }
        });
        btnCan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (combinat.getLlista().size() > 0) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Eliminar combinat?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    combinat.getLlista().clear();
                                    omplirLlistaCombinat();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            }

        });
        listCombinat.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
                //Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                //Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeLeft() {
                if (combinat.getLlista().size() > 0) {
                    Combinat combinat2 = new Combinat();
                    combinat2.setLlista(combinat.getLlista());
                    comanda.afegirCombinat(combinat2);
                    omplirLlistaComanda();
                    combinat.getLlista().clear();
                    omplirLlistaCombinat();
                }

            }

            public void onSwipeBottom() {
                //Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }

        });
        listCombinat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                TextView txtCodi = (TextView) view.findViewById(R.id.art_comb_id);
                final int codi = Integer.parseInt(txtCodi.getText().toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Confirmar");
                builder.setMessage("Esborrar Linia?");

                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        combinat.esborrarArticle(codi);
                        omplirLlistaCombinat();
                        dialog.dismiss();
                    }

                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        listComanda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, DialegOpcions.class);
                TextView txtCodi = (TextView) view.findViewById(R.id.combinat_id);
                int codi = Integer.parseInt(txtCodi.getText().toString());
                i.putExtra("codi", codi);
                startActivity(i);
                /*final Combinat combinat_actual = MainActivity.comanda.trobaCombinat(codi);
                combinat_actual.setQuantitat(combinat_actual.getQuantitat()+1);
                omplirLlistaComanda();*/
                //Toast.makeText(MainActivity.this, comanda.trobaCombinat(codi).getId()+"", Toast.LENGTH_SHORT).show();
            }
        });
        btnCobrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float total = 0;
                for(int i = 0;i<comanda.getLlista().size();i++){
                    total = total + comanda.getLlista().get(i).getPreuTotal()*comanda.getLlista().get(i).getQuantitat();
                }
                if(comanda.getLlista().size()>0){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("TOTAL: " + total + " €")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    comanda.getLlista().clear();
                                    omplirLlistaComanda();
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();
                }
            }
        });
        btnElimComanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comanda.getLlista().size()>0){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("ELIMINAR COMANDA?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    comanda.getLlista().clear();
                                    omplirLlistaComanda();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void carregarArticles() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(getAssets().open("basededades.txt")));
        Article article;
        String[] dades;
        String linia=bf.readLine();
        while (linia!=null){
            dades = linia.split(",");
            //Log.i("ARITICLE", dades[0]);
            article=new Article(Integer.parseInt(dades[0]),dades[1],dades[2],Float.parseFloat(dades[3]),dades[4]);
            bd.afegirArticle(article);
            linia=bf.readLine();
        }
    }
    public void omplirLlistaComanda(){
        listComanda.setAdapter(new ListAdapterComanda(comanda.getLlista()));
    }
    public void omplirGridArt(String familia){
        gridArticles.setAdapter(new GridAdapterRefrescos(bd.getArtFamilia(familia)));
    }
    public void omplirLlistaCombinat(){
        listCombinat.setAdapter(new ListAdapterCombinat(combinat.getLlista()));
    }
    public void carregarRefrescos(){
        gridRefrescos.setAdapter(new GridAdapterRefrescos(bd.getArtFamilia("refresc")));
    }

    public void carregarFamilies(){
        LinkedList<String> families = new LinkedList<String>();
        families = bd.getFamilies();
        families.addFirst("combinats predeterminats");
        gridFamilies.setAdapter(new ListAdapterFamilies(families));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        omplirLlistaCombinat();
        omplirLlistaComanda();
    }
    public Comanda getComanda(){
        return comanda;
    }
}

package com.example.sergi.fastcocktail;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by sergi on 23/11/2015.
 */
public class ListAdapterComanda extends BaseAdapter {

    private LinkedList<Combinat> llista=new LinkedList<Combinat>();
    private int mCount;

    ListAdapterComanda(LinkedList<Combinat> llista) {
        mCount=llista.size();
       this.llista=llista;
    }

    public int getCount() {
        return mCount;
    }

    public long getItemId(final int position) {
        return position;
    }

    public View getView(final int position, final View convertView,
                        final ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.fila_comanda, parent, false);
            //view.setBackgroundResource(R.drawable.sombra_articles);
            view.setMinimumHeight(50);
        }

        TextView textNom=(TextView)view.findViewById(R.id.combinat_nom);
        TextView textPreu=(TextView)view.findViewById(R.id.combinat_preu);
        TextView textId=(TextView)view.findViewById(R.id.combinat_id);
        TextView textQuant=(TextView)view.findViewById(R.id.combinat_quantitat);
        String noms="";
        for(int i = 0;i<llista.get(position).getLlista().size();i++){
            if(i==llista.get(position).getLlista().size()-1){
                noms = noms +llista.get(position).getLlista().get(i).getNom();
            }
            else{
                noms = noms +llista.get(position).getLlista().get(i).getNom()+"+";
            }

        }
        float preu = 0;
        for(int i = 0;i<llista.get(position).getLlista().size();i++){
            preu = preu + llista.get(position).getLlista().get(i).getPreu();
        }
        textNom.setText(noms);
        /*textNom.setMovementMethod(new ScrollingMovementMethod());
        textNom.setHorizontallyScrolling(true);*/
        textPreu.setText(preu+" €");
        textId.setText(llista.get(position).getId()+"");
        textQuant.setText(llista.get(position).getQuantitat()+"");
        //textNom.setHeight(100);
        return view;
    }

    public Object getItem(int position) {
        return null;
    }
}

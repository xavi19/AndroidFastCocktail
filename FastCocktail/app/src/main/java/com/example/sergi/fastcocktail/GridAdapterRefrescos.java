package com.example.sergi.fastcocktail;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.LinkedList;

/**
 * Created by sergi on 20/11/2015.
 */
public class GridAdapterRefrescos extends BaseAdapter {

    private LinkedList<Article> llista;
    private int mCount;

    GridAdapterRefrescos(LinkedList<Article> llista) {
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
                    R.layout.fila_article, parent, false);
            view.setBackgroundResource(R.drawable.estat_art_grid);
            view.setMinimumHeight(130);
        }

        TextView textNom=(TextView)view.findViewById(R.id.txtNomArt);
        TextView textCodi=(TextView)view.findViewById(R.id.txtIdArt);
        TextView textPreu = (TextView)view.findViewById(R.id.txtPreuArt);
        ImageView img = (ImageView) view.findViewById(R.id.imatgeArticle);
        //Log.i("NOM REFRESC",llista.get(position).getId()+"");
        textCodi.setText(llista.get(position).getId() + "");
        textNom.setText(llista.get(position).getNom().toLowerCase());
        if(textNom.getLineCount()>1){
            textNom.setTextSize(11);
        }
        textPreu.setText(llista.get(position).getPreu() + " € ");
        if(!(llista.get(position).getRutafoto().equals("a"))){
            //Log.i("RUTA",imatge);
            img.setImageResource(view.getResources().getIdentifier(llista.get(position).getRutafoto(), "drawable", BuildConfig.APPLICATION_ID));
        }
        //textNom.setHeight(100);
        return view;
    }

    public Object getItem(int position) {
        return null;
    }
}

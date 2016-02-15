package com.example.sergi.fastcocktail;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by sergi on 20/11/2015.
 */
public class ListAdapterCombinat extends BaseAdapter {

	private LinkedList<Article> llista;
	private int mCount;

	ListAdapterCombinat(LinkedList<Article> llista) {
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
					R.layout.fila_art_comb, parent, false);
			//view.setBackgroundResource(R.drawable.sombra_articles);
			view.setMinimumHeight(50);
		}

		TextView textNom=(TextView)view.findViewById(R.id.art_comb_nom);
		TextView textCodi=(TextView)view.findViewById(R.id.art_comb_id);
		TextView textPreu=(TextView)view.findViewById(R.id.art_comb_preu);
		//Log.i("NOM REFRESC",llista.get(position).getNom());
		textCodi.setText(llista.get(position).getId()+"");
		textNom.setText(llista.get(position).getNom().toLowerCase());
		textPreu.setText(llista.get(position).getPreu()+" €");
		//textNom.setHeight(100);
		return view;
	}

	public Object getItem(int position) {
		return null;
	}
}

package com.example.sergi.fastcocktail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by sergi on 20/11/2015.
 */
public class ListAdapterFamilies extends BaseAdapter {

	private LinkedList<String> llista;
	private int mCount;

	ListAdapterFamilies(LinkedList<String>  llista) {
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
					R.layout.fila_familia, parent, false);
			view.setBackgroundResource(R.drawable.estat_blau);
			view.setMinimumHeight(70);
		}

		TextView textNom=(TextView)view.findViewById(R.id.txtFamilia);
		textNom.setText(llista.get(position).toUpperCase());

		//Log.i("NOM REFRESC",llista.get(position).getNom());
		//textNom.setHeight(100);
		return view;
	}

	public Object getItem(int position) {
		return null;
	}
}

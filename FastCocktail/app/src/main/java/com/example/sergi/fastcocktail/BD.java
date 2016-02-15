package com.example.sergi.fastcocktail;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by XAVI on 20/11/2015.
 */
public class BD {
    private LinkedList<Article> llista;

    public BD() {
       llista = new LinkedList<Article>();
    }

    public void afegirArticle(Article art){
        if (!(llista.contains(art.getId()))){
            llista.add(art);
        }
    }
    public void esborrarArticle(String nom){

    }

    public LinkedList<Integer> getIds(){
        LinkedList<Integer> ids = new LinkedList<Integer>();
        for(int i=0;i<llista.size();i++){
            ids.add(llista.get(i).getId());
        }
        return ids;
    }
    public LinkedList<String> getFamilies(){
        LinkedList<String> llistaFamilia = new LinkedList<String>();
        for (int i=0;i<llista.size();i++) {
            if (!(llistaFamilia.contains(llista.get(i).getFamilia())) && !llista.get(i).getFamilia().equals("refresc")) {
                llistaFamilia.add(llista.get(i).getFamilia());
            }
        }
        return llistaFamilia;
    }
    public LinkedList<String> getNoms(){
        LinkedList<String> ids = new LinkedList<String>();
        for(int i=0;i<llista.size();i++){
            ids.add(llista.get(i).getNom());
        }
        return ids;
    }

    public Article getArticle(int id){
        boolean trobat = false;
        int i = 0;
        Article art = null;
        while (!trobat && i<llista.size()){
            if(llista.get(i).getId()==id){
                art = llista.get(i);
                trobat = true;
            }
            i++;
        }
        return art;
    }
    public LinkedList<Article> getLlista(){
        return llista;
    }
    public LinkedList<Article> getArtFamilia(String familia){
        LinkedList<Article> llistaFamilia = new LinkedList<Article>();
        for(int i=0;i<llista.size();i++){
            if(llista.get(i).getFamilia().equals(familia)){
                llistaFamilia.add(llista.get(i));
            }
        }
        return llistaFamilia;
    }
}

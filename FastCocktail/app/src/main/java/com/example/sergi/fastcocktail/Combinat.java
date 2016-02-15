package com.example.sergi.fastcocktail;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sergi on 12/11/2015.
 */
public class Combinat {

    private int id;
    private int quantitat;
    private LinkedList<Article> llista;

    public Combinat() {

        llista=new LinkedList<Article>();
        quantitat = 1;
    }

    public void afegirArticle(Article article){

        llista.addFirst(article);
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void esborrarArticle(int id){
        boolean trobat = false;
        int i = 0;
        while(!trobat && i<llista.size()){
            if(llista.get(i).getId()==id){
                trobat = true;
            }
            else{
                i++;
            }
        }
        if(trobat){
            llista.remove(i);

        }
    }

    public LinkedList<Article> getLlista(){
        return llista;
    }
    public void setLlista(LinkedList<Article> ll){
        LinkedList<Article> llista_copia = new LinkedList<Article>();
        llista_copia.addAll(ll);
        llista= llista_copia;
    }
    public float getPreuTotal(){
        float preu = 0;
        for(int i = 0;i<llista.size();i++){
            preu = preu + llista.get(i).getPreu();
        }
        return preu;
    }

}

package com.example.sergi.fastcocktail;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sergi on 12/11/2015.
 */
public class Comanda {

    private int ids;
    private LinkedList<Combinat> llista;

    public Comanda(){
        llista = new LinkedList<Combinat>();
        ids=0;
    }

    public void afegirCombinat(Combinat combinat){
        combinat.setId(ids);
        llista.addFirst(combinat);
        ids++;
    }
    public Combinat trobaCombinat(int id){
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
            return llista.get(i);
        }
        else{
            return null;
        }
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public void esborrarCombinat(int id){
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

    public LinkedList<Combinat> getLlista(){
        return llista;
    }
}

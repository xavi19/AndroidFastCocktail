package com.example.sergi.fastcocktail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * Created by sergi on 12/11/2015.
 */
public class Article {

    private int id;
    private String familia;
    private String nom;
    private float preu;
    private String rutafoto;

    public Article(int id,String familia, String nom,float preu,String rutafoto){
        this.id=id;
        this.familia=familia;
        this.nom=nom;
        this.preu=preu;
        this.rutafoto=rutafoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public String getRutafoto() {
        return rutafoto;
    }

    public void setRutafoto(String rutafoto) {
        this.rutafoto = rutafoto;
    }
}

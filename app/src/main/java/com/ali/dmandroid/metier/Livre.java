package com.ali.dmandroid.metier;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String type;
    private int userId;
    private static List<Livre> listeLivres = new ArrayList<Livre>();

    public Livre(Cursor c) {
        this.id = c.getInt(0);
        this.titre = c.getString(1);
        this.auteur = c.getString(2);
        this.type = c.getString(3);
        this.userId = c.getInt(4);
    }
    public Livre(String titre, String auteur, String type, int userId) {
        this.titre = titre;
        this.auteur = auteur;
        this.type = type;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static List<Livre> getListeLivres() {
        return listeLivres;
    }

    public static void setListeLivres(List<Livre> listeLivres) {
        Livre.listeLivres = listeLivres;
    }
}

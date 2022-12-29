package com.ali.dmandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ali.dmandroid.metier.Livre;
import com.ali.dmandroid.metier.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "bibliodb";
    // users table
    private static final String TABLE_USERS = "users";
    private static final String COL_ID_USER = "id";
    private static final int NUM_COL_ID_USER = 0;
    private static final String COL_USERNAME = "username";
    private static final int NUM_COL_USERNAME = 1;
    private static final String COL_PASSWORD = "password";
    private static final int NUM_COL_PASSWORD = 2;
    // livres table
    private static final String TABLE_LIVRES = "livres";
    private static final String COL_ID_LIVRE = "id";
    private static final int NUM_COL_ID_LIVRE = 0;
    private static final String COL_TITRE = "titre";
    private static final int NUM_COL_TITRE= 1;
    private static final String COL_AUTEUR = "auteur";
    private static final int NUM_COL_AUTEUR = 2;
    private static final String COL_TYPE = "type";
    private static final int NUM_COL_TYPE = 3;
    private static final String COL_USER_ID = "user_id";
    private static final int NUM_COL_USER_ID = 3;

    private SQLiteDatabase sqLiteDatabase;
    private DBOpenHelper dbOpenHelper;

    public DAO(Context context) {
        this.dbOpenHelper = new DBOpenHelper(context, NOM_BDD, null, VERSION_BDD);
    }
    public void open(){
        this.sqLiteDatabase = dbOpenHelper.getWritableDatabase();
    }
    public void close(){
        this.sqLiteDatabase.close();
    }

    // User
    public long saveUser(User user){
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, user.getUsername());
        values.put(COL_PASSWORD, user.getPassword());
        return sqLiteDatabase.insert(TABLE_USERS, null, values);
    }

    public User checkUserExistance(User user){
        Cursor c = sqLiteDatabase.query(TABLE_USERS, null,
                COL_USERNAME + " = '" + user.getUsername() + "' and "+ COL_PASSWORD + " = '" + user.getPassword() + "';",
                null, null, null, null);
        if(c.getCount()==0) return null;
        c.moveToFirst();
        return new User(c);
    }

    public User getUserByUserName(String username){
        Cursor c = sqLiteDatabase.query(TABLE_USERS, null,
                COL_USERNAME + " = '" + username +"'", null, null, null, null);
        if(c.getCount()==0) return null;
        c.moveToFirst();
        return new User(c);
    }

    public int getUsersCount(){
        Cursor c = sqLiteDatabase.query(TABLE_USERS, new String[]{"count(*)"},
                null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(0);
    }

    // Livre
    public long saveLivre(Livre livre){
        ContentValues values = new ContentValues();
        values.put(COL_TITRE, livre.getTitre());
        values.put(COL_AUTEUR, livre.getAuteur());
        values.put(COL_TYPE, livre.getType());
        values.put(COL_USER_ID, livre.getUserId());
        return sqLiteDatabase.insert(TABLE_LIVRES, null, values);
    }

    public List<Livre> allLivres(){
        List<Livre> liste = new ArrayList<Livre>();
        Cursor c = sqLiteDatabase.query(TABLE_LIVRES, null,
                null, null, null, null, null);
        while (c.moveToNext()){
            liste.add(new Livre(c));
        }
        return liste;
    }

    public int empruntLivre(Livre livre){
        ContentValues values = new ContentValues();
        values.put(COL_TITRE, livre.getTitre());
        values.put(COL_AUTEUR, livre.getAuteur());
        values.put(COL_TYPE, livre.getType());
        values.put(COL_USER_ID, User.getCurrentUser().getId());
        return sqLiteDatabase.update(TABLE_LIVRES, values, COL_ID_LIVRE + " = ?", new String[] {String.valueOf(livre.getId())});
    }

    public List<Livre> allLivresEmprunter(){
        List<Livre> liste = new ArrayList<Livre>();
        Cursor c = sqLiteDatabase.query(TABLE_LIVRES, null,
                COL_USER_ID + " = "+User.getCurrentUser().getId(), null, null, null, null);
        while (c.moveToNext()){
            liste.add(new Livre(c));
        }
        return liste;
    }

    public int getLivresCount(){
        Cursor c = sqLiteDatabase.query(TABLE_LIVRES, new String[]{"count(*)"},
                null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(0);
    }
}

package com.ali.dmandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    // user table
    private static final String TABLE_USERS = "users";
    private static final String COL_ID_USER = "id";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";
    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
            + COL_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_USERNAME + " TEXT NOT NULL, "
            + COL_PASSWORD + " TEXT NOT NULL);";
    //livres table
    private static final String TABLE_LIVRES = "livres";
    private static final String COL_ID_LIVRE = "id";
    private static final String COL_TITRE = "titre";
    private static final String COL_AUTEUR = "auteur";
    private static final String COL_TYPE = "type";
    private static final String COL_USER_ID = "user_id";

    private static final String CREATE_LIVRES_TABLE = "CREATE TABLE " + TABLE_LIVRES + " ("
            + COL_ID_LIVRE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TITRE + " TEXT NOT NULL, "
            + COL_AUTEUR + " TEXT NOT NULL, "
            + COL_TYPE + " TEXT NOT NULL, "
            + COL_USER_ID + " INTEGER NOT NULL);";

    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_LIVRES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

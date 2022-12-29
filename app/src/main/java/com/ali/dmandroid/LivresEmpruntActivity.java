package com.ali.dmandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.ali.dmandroid.Adapters.LivreAdapter;
import com.ali.dmandroid.database.DAO;
import com.ali.dmandroid.metier.Livre;

public class LivresEmpruntActivity extends AppCompatActivity {
    DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livres_emprunt);
        dao = new DAO(this);
        dao.open();
        Livre.setListeLivres(dao.allLivresEmprunter());
        dao.close();
        ListView listView = findViewById(R.id.liste_livres);
        LivreAdapter adapter = new LivreAdapter(this, Livre.getListeLivres(), true);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dao.open();
        Livre.setListeLivres(dao.allLivresEmprunter());
        dao.close();
    }
}
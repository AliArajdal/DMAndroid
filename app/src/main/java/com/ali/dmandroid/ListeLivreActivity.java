package com.ali.dmandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.ali.dmandroid.Adapters.LivreAdapter;
import com.ali.dmandroid.database.DAO;
import com.ali.dmandroid.metier.Livre;

public class ListeLivreActivity extends AppCompatActivity {
    DAO dao;
    LivreAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_livre);
        ListView listView = findViewById(R.id.liste_livres);
        dao = new DAO(this);
        dao.open();
        if(dao.getLivresCount() == 0){
            for(int i=0; i<4; i++){
                dao.saveLivre(new Livre("livre "+(i+1), "auteur "+(i+1),
                        i % 2 == 0 ? "comedie" : "drame", 0));
            }
        }
        Livre.setListeLivres(dao.allLivres());
        dao.close();
        adapter = new LivreAdapter(this, Livre.getListeLivres(), false);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dao.open();
        Livre.setListeLivres(dao.allLivres());
        dao.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.liste_livres_item:
                dao.open();
                Livre.setListeLivres(dao.allLivres());
                dao.close();
                adapter.notifyDataSetChanged();
                return true;
            case R.id.liste_livres_emprunter_item:
                Intent i = new Intent(this, LivresEmpruntActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
package com.ali.dmandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.dmandroid.R;
import com.ali.dmandroid.database.DAO;
import com.ali.dmandroid.metier.Livre;
import com.ali.dmandroid.metier.User;

import java.util.List;

public class LivreAdapter extends BaseAdapter {
    List<Livre> livres;
    LayoutInflater inflater;
    Context context;
    boolean emprunt;
    public LivreAdapter(Context context, List<Livre> livres, boolean emprunt) {
        inflater = LayoutInflater.from(context);
        this.livres = livres;
        this.context = context;
        this.emprunt = emprunt;
    }

    @Override
    public int getCount() {
        return livres.size();
    }

    @Override
    public Object getItem(int position) {
        return livres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class Item{
        TextView tvTitre;
        Button btnEmprunt;
    }
    private class Item2{
        TextView tvTitre;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item;
        Item2 item2;
        if(emprunt){
            if(convertView == null){
                item2 = new Item2();
                convertView = inflater.inflate(R.layout.livre_emprunt_item, null);
                item2.tvTitre = (TextView) convertView.findViewById(R.id.titreEmp);
                convertView.setTag(item2);
            }else{
                item2 = (Item2) convertView.getTag();
            }
            item2.tvTitre.setText(livres.get(position).getTitre());
        }else{
            if(convertView == null){
                item = new Item();
                convertView = inflater.inflate(R.layout.livre_item, null);
                item.tvTitre = (TextView) convertView.findViewById(R.id.titreId);
                item.btnEmprunt = (Button) convertView.findViewById(R.id.empruntBtn);
                convertView.setTag(item);
            }else{
                item = (Item) convertView.getTag();
            }
            item.tvTitre.setText(livres.get(position).getTitre());

            Button empruntBnt = (Button) convertView.findViewById(R.id.empruntBtn);
            empruntBnt.setOnClickListener(v -> {
                Livre livre = Livre.getListeLivres().get(position);
                DAO dao = new DAO(context);
                if(livre.getUserId() != User.getCurrentUser().getId()){
                    dao.open();
                    dao.empruntLivre(livre);
                    Toast.makeText(context, livre.getTitre()+" emprunté", Toast.LENGTH_SHORT).show();
                    dao.close();
                }else{
                    Toast.makeText(context, "Livre déjà emprunté", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }
}

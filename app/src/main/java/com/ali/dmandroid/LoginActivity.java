package com.ali.dmandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ali.dmandroid.database.DAO;
import com.ali.dmandroid.metier.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText etUsername = findViewById(R.id.username);
        EditText etPassword = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.login_button);

        DAO dao = new DAO(this);
        dao.open();
        if(dao.getUsersCount() == 0){
            dao.saveUser(new User("user", "user"));
            dao.close();
        }

        loginBtn.setOnClickListener(v -> {
            dao.open();
            User user = dao.checkUserExistance( new User(
                            etUsername.getText().toString(),
                            etPassword.getText().toString())
            );
            if(user == null){
                Toast.makeText(this, "Username ou Password invalid", Toast.LENGTH_SHORT).show();
                dao.close();
            }else{
                User.setCurrentUser(dao.getUserByUserName(user.getUsername()));
                Intent i = new Intent(LoginActivity.this, ListeLivreActivity.class);
                dao.close();
                startActivity(i);
            }
        });
    }
}
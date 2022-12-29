package com.ali.dmandroid.metier;

import android.database.Cursor;

public class User {
    private int id;
    private String username;
    private String password;
    private static User currentUser = new User();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Cursor c) {
        this.id = c.getInt(0);
        this.username = c.getString(1);
        this.password = c.getString(2);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }
}

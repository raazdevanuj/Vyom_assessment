package com.example.vyom_assessment;

import android.app.Application;

public class CurrentUser extends Application {
    String name="",email="";

    public CurrentUser() {
    }

    public CurrentUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

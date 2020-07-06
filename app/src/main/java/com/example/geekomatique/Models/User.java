/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : User.java
 * Edited by pinbe
 */

package com.example.geekomatique.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User implements Serializable {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String login;


    public User(String email, String login, String firstName, String lastName) {
        this.email = email;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public HashMap<String, String> getHashMap(String password){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("email", getEmail());
        hashMap.put("password", password);
        hashMap.put("lastName", getLastName());
        hashMap.put("firstName", getFirstName());
        hashMap.put("login", getLogin());

        return hashMap;
    }
}

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
    private String roleName;

    public User(long id, String email, String firstName, String lastName, String login, String roleName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.roleName = roleName;
    }

    public User(String email, String firstName, String lastName, String login, String roleName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.roleName = roleName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public HashMap<String, String> getHashMap(String password){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("email", getEmail());
        hashMap.put("password", password);
        hashMap.put("lastName", getLastName());
        hashMap.put("firstName", getFirstName());
        hashMap.put("login", getLogin());
        hashMap.put("roleName", getRoleName());

        return hashMap;
    }
}

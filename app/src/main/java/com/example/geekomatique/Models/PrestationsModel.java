/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : PrestationsModel.java
 * Edited by pinbe
 */

package com.example.geekomatique.Models;

import java.util.HashMap;

public class PrestationsModel {
    //Model d'une prestation

    private int id;
    private String name;
    private String price;

    public PrestationsModel(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public PrestationsModel(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public HashMap<String, String> getPrestationHashMap(String password){
        HashMap<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("name", getName());
        hashMap.put("price", String.valueOf(getPrice()));
        return hashMap;
    }
}

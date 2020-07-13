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

    private Integer id;
    private String name;
    private String price;
    private Integer quantity;

    public PrestationsModel(Integer id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    public PrestationsModel(String name, String price) {
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
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

    @Override
    public String toString() {
        return name + " - Prix: "+ price;
    }
}

/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : Prestations.java
 * Edited by pinbe
 */

package com.example.geekomatique.Models;

public class Prestations {

    private long id;
    private String name;
    private String price;

    public Prestations(long id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Prestations(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
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
}

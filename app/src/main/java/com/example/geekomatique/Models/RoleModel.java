/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : RoleModel.java
 * Edited by pinbe
 */

package com.example.geekomatique.Models;

public class RoleModel {

    private Integer id;
    private String name;


    public RoleModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/*
 * Copyright (c) 2020
 * Project: Geekomatique_Android
 * File : AddressModel.java
 * Edited by lamyg
 */

package com.example.geekomatique.Models;

public class AddressModel {
    private Integer id;
    private String street;
    private String city;
    private Integer zipcode;
    private String country;
    private String type;
    private Integer userId;

    public AddressModel(Integer id, String street, String city, Integer zipcode, String country, String type, Integer userId) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
        this.type = type;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

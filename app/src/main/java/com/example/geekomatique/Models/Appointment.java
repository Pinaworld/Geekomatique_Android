/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : Appointment.java
 * Edited by pinbe
 */

package com.example.geekomatique.Models;

import java.util.Date;

public class Appointment {

    private long id;
    private Date date;
    private String description;
    private boolean remote;
    private boolean done;
    private String userId;
    private String adressId;


        public Appointment(Date date, String description, boolean remote, boolean done,String userId, String adressId) {

            this.date= date;
            this.description = description;
            this.remote = remote ;
            this.done = done;
            this.userId = userId;
            this.adressId = adressId ;
        }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdressId() {
        return adressId;
    }

    public void setAdressId(String adressId) {
        this.adressId = adressId;
    }
}

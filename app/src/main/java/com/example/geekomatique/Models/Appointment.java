/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : Appointment.java
 * Edited by pinbe
 */

package com.example.geekomatique.Models;

import java.util.Date;

public class Appointment {

    private int id;
    private String date;
    private String description;
    private boolean remote;
    private boolean done;
    private int userId;
    private int adressId;
    private boolean canceled;
    private boolean validate;

    public Appointment(int id, String date, String description, boolean remote, boolean done, int userId, int adressId, boolean canceled, boolean validate) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.remote = remote;
        this.done = done;
        this.userId = userId;
        this.adressId = adressId;
        this.canceled = canceled;
        this.validate = validate;
    }

    public Appointment(String date, String description, boolean remote, boolean done, int userId, int adressId, boolean canceled, boolean validate) {
        this.date = date;
        this.description = description;
        this.remote = remote;
        this.done = done;
        this.userId = userId;
        this.adressId = adressId;
        this.canceled = canceled;
        this.validate = validate;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdressId() {
        return adressId;
    }

    public void setAdressId(int adressId) {
        this.adressId = adressId;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }
}

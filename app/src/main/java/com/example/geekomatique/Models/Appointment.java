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
    private String date;
    private String description;
    private boolean remote;
    private boolean done;
    private long userId;
    private long adressId;
    private boolean canceled;
    private boolean validate;

    public Appointment(long id, String date, String description, boolean remote, boolean done, long userId, long adressId, boolean canceled, boolean validate) {
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

    public Appointment(String date, String description, boolean remote, boolean done, long userId, long adressId, boolean canceled, boolean validate) {
        this.date = date;
        this.description = description;
        this.remote = remote;
        this.done = done;
        this.userId = userId;
        this.adressId = adressId;
        this.canceled = canceled;
        this.validate = validate;
    }

    public long getId() {
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAdressId() {
        return adressId;
    }

    public void setAdressId(long adressId) {
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

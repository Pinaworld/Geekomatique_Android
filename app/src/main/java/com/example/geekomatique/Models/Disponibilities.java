/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : Disponibilities.java
 * Edited by pinbe
 */

package com.example.geekomatique.Models;

import java.sql.Time;

public class Disponibilities {

    private long id;
    private String start; //Should be a time type
    private String end; //Should be a time type
    private String title;
    private long day_number;
    private long employee_id;

    public Disponibilities(long id, String start, String end, String title, long day_number, long employee_id) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.title = title;
        this.day_number = day_number;
        this.employee_id = employee_id;
    }

    public Disponibilities(String start, String end, String title, long day_number, long employee_id) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.day_number = day_number;
        this.employee_id = employee_id;
    }

    public long getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDay_number() {
        return day_number;
    }

    public void setDay_number(long day_number) {
        this.day_number = day_number;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }
}
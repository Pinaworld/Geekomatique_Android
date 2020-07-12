/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : DisponibilitiesModel.java
 * Edited by pinbe
 */

package com.example.geekomatique.Models;

import java.util.HashMap;

public class DisponibilitiesModel {

    private int id;
    private String start; //Should be a time type
    private String end; //Should be a time type
    private String title;
    private int day_number;
    private int employee_id;

    public DisponibilitiesModel(int id, String start, String end, String title, int day_number, int employee_id) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.title = title;
        this.day_number = day_number;
        this.employee_id = employee_id;
    }

    public DisponibilitiesModel(String start, String end, String title, int day_number, int employee_id) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.day_number = day_number;
        this.employee_id = employee_id;
    }

    public int getId() {
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

    public int getDay_number() {
        return day_number;
    }

    public void setDay_number(int day_number) {
        this.day_number = day_number;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }


    public HashMap<String, String> getDisponibilityHashMap(){
        HashMap<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("start", getStart());
        hashMap.put("end", getEnd());
        hashMap.put("title", getTitle());
        hashMap.put("day_number", String.valueOf(getDay_number()));
        hashMap.put("employee_id",String.valueOf(getEmployee_id()));
        return hashMap;
    }
}
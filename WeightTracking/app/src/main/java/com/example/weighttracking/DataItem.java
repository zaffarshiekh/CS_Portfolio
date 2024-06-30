package com.example.weighttracking.model;

public class DataItem {
    private int id;
    private double weight;
    private String date;

    public DataItem(int id, double weight, String date) {
        this.id = id;
        this.weight = weight;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public String getDate() {
        return date;
    }
}
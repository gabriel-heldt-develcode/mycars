package com.example.mycars.model;

public class VehiclesModel {

    private int id;
    private String name;
    private int year;
    private double price;
    private String image;

    public VehiclesModel() {
    }

    public VehiclesModel(int id, String name, int year, double price, String image) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

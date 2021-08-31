package com.example.mycars.model;

import java.math.BigDecimal;

public class VehiclesModel {

    private int id;
    private String model;
    private int year;
    private BigDecimal price;
    private String image;

    public VehiclesModel() {
    }

    public VehiclesModel(int id, String model, int year, BigDecimal price, String image) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.price = price;
        this.image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public void setPrice(BigDecimal price) {
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

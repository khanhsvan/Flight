package com.main.adminpj.model;


import java.math.BigDecimal;

public class luggage {
    private int id;
    private int quantity;
    private BigDecimal weight;
    private String type;
    private String description;

    //getter setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // contructor


    public luggage() {
    }

    public luggage(int id, int quantity, BigDecimal weight, String type, String description) {
        this.id = id;
        this.quantity = quantity;
        this.weight = weight;
        this.type = type;
        this.description = description;
    }
}

package com.eric.a2024_teamproject;

public class FoodItem {
    private String name;
    private int foodDate;
    private int foodEnddate;

    public FoodItem(String name, int foodDate, int foodEnddate) {
        this.name = name;
        this.foodDate = foodDate;
        this.foodEnddate = foodEnddate;
    }

    public String getName() {
        return name;
    }

    public int getFoodDate() {
        return foodDate;
    }

    public int getFoodEnddate() {
        return foodEnddate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFoodDate(int foodDate) {
        this.foodDate = foodDate;
    }

    public void setFoodEnddate(int foodEnddate) {
        this.foodEnddate = foodEnddate;
    }
}
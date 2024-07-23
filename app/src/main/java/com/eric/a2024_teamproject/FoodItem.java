package com.eric.a2024_teamproject;

public class FoodItem {
    private String name;
    private int foodDate;   // 등록일
    private int foodEnddate; // 소비기한
    private int imageResId;

    public FoodItem(String name, int foodDate, int foodEnddate, int imageResId) {
        this.name = name;
        this.foodDate = foodDate;
        this.foodEnddate = foodEnddate;
        this.imageResId = imageResId;
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

    public int getImageResId() {
        return imageResId;
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

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}

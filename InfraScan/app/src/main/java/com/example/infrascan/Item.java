package com.example.infrascan;
public class Item {
    private final int imageResId;
    private final String description;
    private final String percentage;

    public Item(int imageResId, String description, String percentage) {
        this.imageResId = imageResId;
        this.description = description;
        this.percentage = percentage;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }

    public String getPercentage() {
        return percentage;
    }
}

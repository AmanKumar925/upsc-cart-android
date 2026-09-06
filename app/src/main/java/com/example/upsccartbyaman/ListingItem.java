package com.example.upsccartbyaman;

public class ListingItem {

    private final String name;
    private final String price;
    private final String condition;
    private final String category;
    private final String area;
    private final String description;
    private final String phone;

    private final int imageRes;
    private final String imageUri;

    // =========================================================
    // OLD CONSTRUCTOR - EXISTING ITEMS
    // =========================================================

    public ListingItem(
            String name,
            String price,
            String condition,
            String category,
            String area,
            String description,
            String phone,
            int imageRes
    ) {

        this.name = name;
        this.price = price;
        this.condition = condition;
        this.category = category;
        this.area = area;
        this.description = description;
        this.phone = phone;
        this.imageRes = imageRes;
        this.imageUri = "";
    }

    // =========================================================
    // NEW CONSTRUCTOR - POSTED ITEMS
    // =========================================================

    public ListingItem(
            String name,
            String price,
            String condition,
            String category,
            String area,
            String description,
            String phone,
            String imageUri
    ) {

        this.name = name;
        this.price = price;
        this.condition = condition;
        this.category = category;
        this.area = area;
        this.description = description;
        this.phone = phone;
        this.imageRes = 0;
        this.imageUri = imageUri == null ? "" : imageUri;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getCondition() {
        return condition;
    }

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getImageUri() {
        return imageUri;
    }
}
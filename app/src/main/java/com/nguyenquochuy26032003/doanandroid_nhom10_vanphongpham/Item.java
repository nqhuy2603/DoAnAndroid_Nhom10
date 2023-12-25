package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham;

public class Item {
    private int id;
    private int idc;
    private String name;
    private double price;
    private int quantity;
    private String describe;
    private int image;

    public Item(int id, int idc, String name, float price, int quantity, String describe, int image) {
        this.id = id;
        this.idc = idc;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.describe = describe;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

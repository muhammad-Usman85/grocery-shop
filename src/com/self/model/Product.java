package com.self.model;

public class Product {

    private int productId; // product_id
    private String productName; // product_name

    public Product() {
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public Product(int productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}

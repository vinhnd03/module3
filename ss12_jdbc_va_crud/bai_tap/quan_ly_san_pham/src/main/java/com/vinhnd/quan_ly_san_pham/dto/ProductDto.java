package com.vinhnd.quan_ly_san_pham.dto;

public class ProductDto {
    private Integer productId;
    private String productName;
    private float price;
    private boolean status;
    private Integer categoryId;
    private String categoryName;

    public ProductDto() {
    }

    public ProductDto(Integer productId, String productName, float price,
                      boolean status, Integer categoryId, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

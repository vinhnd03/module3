package com.vinhnd.quan_ly_san_pham.entity;

import com.vinhnd.quan_ly_san_pham.util.UpdateIdUtil;

import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private float price;
    private String description;
    private boolean status;

    public Product() {
    }

    public Product(String name, float price, String description, boolean status) {
        this.id = ++UpdateIdUtil.currentId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    public Product(Integer id, String name, float price, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

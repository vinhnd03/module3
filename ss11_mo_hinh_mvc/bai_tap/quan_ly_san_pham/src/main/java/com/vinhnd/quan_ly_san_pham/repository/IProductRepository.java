package com.vinhnd.quan_ly_san_pham.repository;

import com.vinhnd.quan_ly_san_pham.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    void add(Product product);
    void update(Product product);
    void delete(Product product);
    Product findById(Integer id);
    List<Product> findByName(String name);
}

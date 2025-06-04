package com.vinhnd.quan_ly_san_pham.service;

import com.vinhnd.quan_ly_san_pham.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void add(Product product);

    Product findById(Integer id);

    void update(Product product);

    void delete(Product product);

    List<Product> findByName(String searchName);
}

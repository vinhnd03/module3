package com.vinhnd.quan_ly_san_pham.service;

import com.vinhnd.quan_ly_san_pham.dto.ProductDto;
import com.vinhnd.quan_ly_san_pham.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    boolean add(Product product);

    Product findById(Integer id);

    boolean update(Product product);

    boolean delete(Product product);

    List<Product> findByName(String searchName);

    List<Product> findPaginated(int offset, int size);

    int count();

    List<ProductDto> findAllProductWithCategory();

    List<ProductDto> findByNameOrCategoryName(String keyword);
}

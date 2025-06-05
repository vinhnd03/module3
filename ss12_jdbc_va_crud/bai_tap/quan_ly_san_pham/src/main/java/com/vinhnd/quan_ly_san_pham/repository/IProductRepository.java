package com.vinhnd.quan_ly_san_pham.repository;

import com.vinhnd.quan_ly_san_pham.dto.ProductDto;
import com.vinhnd.quan_ly_san_pham.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    boolean add(Product product);
    boolean update(Product product);
    boolean delete(Product product);
    Product findById(Integer id);
    List<Product> findByName(String name);
    List<Product> findPaginated(int offset, int size);
    int count();
    List<ProductDto> findAllProductWithCategory();
    List<ProductDto> findByNameOrCategoryName(String keyword);
}

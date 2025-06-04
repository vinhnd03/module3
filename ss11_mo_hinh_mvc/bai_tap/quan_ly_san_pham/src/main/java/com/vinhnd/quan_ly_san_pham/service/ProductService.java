package com.vinhnd.quan_ly_san_pham.service;

import com.vinhnd.quan_ly_san_pham.entity.Product;
import com.vinhnd.quan_ly_san_pham.repository.IProductRepository;
import com.vinhnd.quan_ly_san_pham.repository.ProductRepository;
import com.vinhnd.quan_ly_san_pham.service.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    private IProductRepository repository = new ProductRepository();

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public void add(Product product) {
        repository.add(product);
    }

    @Override
    public Product findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void update(Product product) {
        repository.update(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }

    @Override
    public List<Product> findByName(String searchName) {
        return repository.findByName(searchName);
    }
}

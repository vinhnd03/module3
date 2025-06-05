package com.vinhnd.quan_ly_san_pham.service;

import com.vinhnd.quan_ly_san_pham.dto.ProductDto;
import com.vinhnd.quan_ly_san_pham.entity.Product;
import com.vinhnd.quan_ly_san_pham.repository.IProductRepository;
import com.vinhnd.quan_ly_san_pham.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {
    private IProductRepository repository = new ProductRepository();

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean add(Product product) {
        return repository.add(product);
    }

    @Override
    public Product findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean update(Product product) {
        return repository.update(product);
    }

    @Override
    public boolean delete(Product product) {
        return repository.delete(product);
    }

    @Override
    public List<Product> findByName(String searchName) {
        return repository.findByName(searchName);
    }

    @Override
    public List<Product> findPaginated(int offset, int size) {
        return repository.findPaginated(offset, size);
    }

    @Override
    public int count() {
        return repository.count();
    }

    @Override
    public List<ProductDto> findAllProductWithCategory() {
        return repository.findAllProductWithCategory();
    }

    @Override
    public List<ProductDto> findByNameOrCategoryName(String keyword) {
        return repository.findByNameOrCategoryName(keyword);
    }
}

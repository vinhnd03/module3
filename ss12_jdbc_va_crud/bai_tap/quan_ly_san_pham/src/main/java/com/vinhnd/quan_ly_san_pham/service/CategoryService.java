package com.vinhnd.quan_ly_san_pham.service;

import com.vinhnd.quan_ly_san_pham.entity.Category;
import com.vinhnd.quan_ly_san_pham.repository.CategoryRepository;
import com.vinhnd.quan_ly_san_pham.repository.ICategoryRepository;

import java.util.List;

public class CategoryService implements ICategoryService{
    private ICategoryRepository repository = new CategoryRepository();


    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }
}

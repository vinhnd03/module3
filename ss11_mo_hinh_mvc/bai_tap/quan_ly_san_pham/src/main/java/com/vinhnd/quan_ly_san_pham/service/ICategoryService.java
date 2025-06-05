package com.vinhnd.quan_ly_san_pham.service;

import com.vinhnd.quan_ly_san_pham.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
}

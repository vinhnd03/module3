package com.vinhnd.quan_ly_san_pham.repository;

import com.vinhnd.quan_ly_san_pham.entity.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> findAll();
}

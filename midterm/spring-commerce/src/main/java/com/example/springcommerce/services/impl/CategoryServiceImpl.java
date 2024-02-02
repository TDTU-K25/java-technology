package com.example.springcommerce.services.impl;

import com.example.springcommerce.models.Category;
import com.example.springcommerce.repository.CategoryRepository;
import com.example.springcommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}

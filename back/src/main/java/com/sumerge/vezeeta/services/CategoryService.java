package com.sumerge.vezeeta.services;

import com.sumerge.vezeeta.exceptions.DataNotFoundException;
import com.sumerge.vezeeta.models.Category;
import com.sumerge.vezeeta.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public Category getCategory(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Category.class, id));
    }

    @Transactional
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}

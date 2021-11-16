package com.sumerge.vezeeta.controllers;

import com.sumerge.vezeeta.models.Category;
import com.sumerge.vezeeta.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}

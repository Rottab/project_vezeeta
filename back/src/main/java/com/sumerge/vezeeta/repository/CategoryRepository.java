package com.sumerge.vezeeta.repository;

import com.sumerge.vezeeta.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findById(Integer id);
    @Override
    List<Category> findAll();

}

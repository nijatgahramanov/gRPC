package com.nijatgahramanov.discountservice.repository;

import com.nijatgahramanov.discountservice.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByExternalId(String id);
}

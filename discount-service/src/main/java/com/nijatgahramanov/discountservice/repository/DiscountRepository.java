package com.nijatgahramanov.discountservice.repository;

import com.nijatgahramanov.discountservice.model.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    Optional<Discount> findByCodeAndCategoryId(String code, Integer categoryId);
}

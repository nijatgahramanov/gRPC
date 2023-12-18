package com.nijatgahramanov.product.service;

import com.nijatgahramanov.product.model.dto.CategoryResponse;
import com.nijatgahramanov.product.model.entity.Category;
import com.nijatgahramanov.product.model.dto.CategoryRequest;
import com.nijatgahramanov.product.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static com.nijatgahramanov.product.mapper.CategoryMapper.INSTANCE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryResponse create(CategoryRequest request) {
        var category = INSTANCE.toCategory(request);
        var savedCategory = categoryRepository.save(category);
        return CategoryResponse.of(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getProducts()
        );
    }

}

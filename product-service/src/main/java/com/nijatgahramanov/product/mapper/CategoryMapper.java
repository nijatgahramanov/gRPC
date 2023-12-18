package com.nijatgahramanov.product.mapper;

import com.nijatgahramanov.product.model.entity.Category;
import com.nijatgahramanov.product.model.dto.CategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryRequest categoryRequest);

}

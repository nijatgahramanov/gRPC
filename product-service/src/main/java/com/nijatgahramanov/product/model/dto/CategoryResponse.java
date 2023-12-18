package com.nijatgahramanov.product.model.dto;

import com.nijatgahramanov.product.model.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

    int id;
    String name;
    List<Product> products;

}

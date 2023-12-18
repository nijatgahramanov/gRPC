package com.nijatgahramanov.product.service;

import com.nijatgahramanov.product.model.entity.Product;
import com.nijatgahramanov.product.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product getById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow( () -> new RuntimeException("Product not found for this productId!"));
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

}

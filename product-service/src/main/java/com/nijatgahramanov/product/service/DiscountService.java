package com.nijatgahramanov.product.service;

import com.nijatgahramanov.grpc.DiscountRequest;
import com.nijatgahramanov.grpc.DiscountResponse;
import com.nijatgahramanov.product.service.grpc.DiscountGrpcService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountService {

    DiscountGrpcService discountGrpcService;
    ProductService productService;

    public DiscountResponse getDiscount(int productId, String code) {
        var product = productService.getById(productId);
        var discountRequest = DiscountRequest.newBuilder()
                .setCode(code)
                .setPrice(product.getPrice().floatValue())
                .setExternalCategoryId(product.getCategory().getId())
                .build();
        return discountGrpcService.getDiscount(discountRequest);
    }

}

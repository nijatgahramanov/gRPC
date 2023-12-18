package com.nijatgahramanov.product.controller;

import com.nijatgahramanov.product.model.dto.DiscountResponse;
import com.nijatgahramanov.product.service.DiscountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/discounts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountController {

    DiscountService discountService;

    @GetMapping
    public ResponseEntity<DiscountResponse> getDiscount(@RequestParam("productId") int productId,
                                                        @RequestParam("code") String code) {
        var discountResponse = discountService.getDiscount(productId, code);
        return ResponseEntity.ok(
                DiscountResponse.of(
                        discountResponse.getNewPrice(),
                        discountResponse.getOldPrice(),
                        discountResponse.getCode()
                )
        );
    }

}



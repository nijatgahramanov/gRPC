package com.nijatgahramanov.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountResponse {

    float newPrice;
    float oldPrice;
    String code;

}

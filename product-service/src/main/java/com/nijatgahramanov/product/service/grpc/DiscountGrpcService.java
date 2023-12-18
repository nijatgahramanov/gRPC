package com.nijatgahramanov.product.service.grpc;

import com.nijatgahramanov.grpc.DiscountRequest;
import com.nijatgahramanov.grpc.DiscountResponse;

public interface DiscountGrpcService {

    DiscountResponse getDiscount(DiscountRequest discountRequest);

}

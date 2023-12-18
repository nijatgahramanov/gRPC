package com.nijatgahramanov.discountservice.service;

import com.nijatgahramanov.discountservice.repository.CategoryRepository;
import com.nijatgahramanov.discountservice.repository.DiscountRepository;
import com.nijatgahramanov.grpc.DiscountRequest;
import com.nijatgahramanov.grpc.DiscountResponse;
import com.nijatgahramanov.grpc.DiscountServiceGrpc;
import com.nijatgahramanov.grpc.Response;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.devh.boot.grpc.server.service.GrpcService;

import java.math.BigDecimal;

@GrpcService
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountServiceImpl extends DiscountServiceGrpc.DiscountServiceImplBase {

    CategoryRepository categoryRepository;
    DiscountRepository discountRepository;

    @Override
    public void getDiscount(DiscountRequest request,
                            StreamObserver<DiscountResponse> responseObserver) {

        try {
            var category = categoryRepository
                    .findByExternalId(String.valueOf(request.getExternalCategoryId()))
                    .orElseThrow(() -> new RuntimeException("Category has not been found as category ID"));

            var discount = discountRepository
                    .findByCodeAndCategoryId(
                            request.getCode(), category.getId()
                    );

            if (discount.isPresent()) {

                var newPrice = discount.get().getDiscountPrice()
                        .subtract(BigDecimal.valueOf(request.getPrice()))
                        .multiply(BigDecimal.valueOf(-1));

                responseObserver.onNext(
                        DiscountResponse.newBuilder()
                                .setCode(discount.get().getCode())
                                .setOldPrice(request.getPrice())
                                .setNewPrice(newPrice.floatValue())
                                .setResponse(
                                        Response.newBuilder()
                                                .setStatusCode(true)
                                                .setMessage("Discount has been applied successfully")
                                                .build()
                                ).build()
                );
                responseObserver.onCompleted();

            } else {
                responseObserver.onNext(
                        DiscountResponse.newBuilder()
                                .setOldPrice(request.getPrice())
                                .setCode(request.getCode())
                                .setResponse(
                                        Response.newBuilder()
                                                .setMessage("Code and category are invalid")
                                                .setStatusCode(false)
                                                .build()
                                ).build()
                );
                responseObserver.onCompleted();
            }

            super.getDiscount(request, responseObserver);
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription("Internal Server Error").asRuntimeException()
            );
        }
    }
}

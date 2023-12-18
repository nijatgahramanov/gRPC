package com.nijatgahramanov.product.service.grpc;

import com.nijatgahramanov.grpc.DiscountRequest;
import com.nijatgahramanov.grpc.DiscountResponse;
import com.nijatgahramanov.grpc.DiscountServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DiscountGrpcServiceImpl implements DiscountGrpcService {

    private DiscountServiceGrpc.DiscountServiceBlockingStub discountServiceBlockingStub;
    private ManagedChannel channel;

    public DiscountGrpcServiceImpl(@Value("${discount.grpc.host}") String grpcHost,
                                   @Value("${discount.grpc.port}") int grpcPort) {
        System.err.println("--> Discount grpc: " + grpcHost + "  " + grpcPort);
        channel = ManagedChannelBuilder
                .forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();
    }

    @Override
    public DiscountResponse getDiscount(DiscountRequest discountRequest) {
        discountServiceBlockingStub = DiscountServiceGrpc.newBlockingStub(channel);
        var discountResponse = discountServiceBlockingStub.getDiscount(discountRequest);
        return discountResponse;
    }

}

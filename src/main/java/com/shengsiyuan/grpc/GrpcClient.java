package com.shengsiyuan.grpc;

import com.shengsiyuan.proto.MyRequest;
import com.shengsiyuan.proto.MyResponse;
import com.shengsiyuan.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Created by yinhao on 2017/7/17.
 */
public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("127.0.0.1",8899).usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(myResponse.getRealname());
        myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(myResponse.getRealname());
        myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(myResponse.getRealname());
        myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(myResponse.getRealname());
        myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(myResponse.getRealname());
        myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(myResponse.getRealname());
    }
}

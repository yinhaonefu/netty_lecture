package com.shengsiyuan.grpc;

import com.shengsiyuan.proto.MyRequest;
import com.shengsiyuan.proto.MyResponse;
import com.shengsiyuan.proto.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * Created by yinhao on 2017/7/17.
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase{
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息：" + request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());//返回响应
        responseObserver.onCompleted();//通知方法执行完毕
    }
}

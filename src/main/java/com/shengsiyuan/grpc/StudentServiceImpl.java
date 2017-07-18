package com.shengsiyuan.grpc;

import com.shengsiyuan.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

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

    @Override
    public void getStudentByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息：" + request.getAge());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(request.getAge()).setCity("北京").build());//返回响应
        responseObserver.onNext(StudentResponse.newBuilder().setName("李四").setAge(request.getAge()).setCity("上海").build());//返回响应
        responseObserver.onNext(StudentResponse.newBuilder().setName("王五").setAge(request.getAge()).setCity("广州").build());//返回响应
        responseObserver.onNext(StudentResponse.newBuilder().setName("赵六").setAge(request.getAge()).setCity("深圳").build());//返回响应
        responseObserver.onCompleted();//通知方法执行完毕
    }

    @Override
    public StreamObserver<StudentRequest> getStudentWrapper(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println(value.getAge());//输出流中每个参数值
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse studentResponse = StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build();
                StudentResponse studentResponse2 = StudentResponse.newBuilder().setName("李四").setAge(30).setCity("上海").build();
                StudentResponseList studentResponseList = StudentResponseList.newBuilder().addStudentResponse(studentResponse).addStudentResponse(studentResponse2).build();
                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println(value.getRequestInfo());//输出流程每个参数
                //返回每个响应到返回流中
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}

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
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);//同步执行
        StudentServiceGrpc.StudentServiceStub nonBlockingStub = StudentServiceGrpc.newStub(managedChannel);//异步执行

        System.out.println("--------------------------------");
        //简单请求简单响应
        MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(myResponse.getRealname());

        System.out.println("--------------------------------");
//        //简单请求流式响应
//        Iterator<StudentResponse> studentResponses = blockingStub.getStudentByAge(StudentRequest.newBuilder().setAge(20).build());
//        while (studentResponses.hasNext()){
//            StudentResponse studentResponse = studentResponses.next();
//            System.out.println(studentResponse.getName());
//            System.out.println(studentResponse.getAge());
//            System.out.println(studentResponse.getCity());
//            System.out.println("=========");
//        }
//
//        System.out.println("--------------------------------");
//
//        //流式请求简单响应
          //第一步 构造方法参数
//        StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
//            @Override
//            public void onNext(StudentResponseList value) {
//                value.getStudentResponseList().forEach(studentResponse -> {
//                    System.out.println(studentResponse.getName() + "," + studentResponse.getAge() + "," + studentResponse.getCity());
//                    System.out.println("==========================");
//                });
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.out.println(t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("completed");
//            }
//        };


//        //如果客户端是以流式的方式发起请求，必须使用异步的方式nonBlockingStub
          //第二步 调用方法，传入参数，客户端以流式方法不断发起请求
//        StreamObserver<StudentRequest> studentRequestStreamObserver = nonBlockingStub.getStudentWrapper(studentResponseListStreamObserver);
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());
//        studentRequestStreamObserver.onCompleted();
//
//        //因为是异步的请求，还没返回结果程序就执行结束了，所以在这里睡眠一下看执行输出
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("--------------------------------");
//
//        //流式请求流式响应
          //直接调用方法，构造参数，然后客户端发起流式请求，不断发送数据
//        StreamObserver<StreamRequest> streamRequestStreamObserver = nonBlockingStub.biTalk(new StreamObserver<StreamResponse>() {
//            @Override
//            public void onNext(StreamResponse value) {
//                System.out.println(value.getResponseInfo());
//                System.out.println("+++++++++++");
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.out.println(t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("completed");
//            }
//        });
//
//        for (int i = 0;i < 10;i++){
//            streamRequestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        streamRequestStreamObserver.onCompleted();
    }
}

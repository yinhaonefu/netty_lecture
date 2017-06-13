package com.shengsiyuan.protobuf;

/**
 * Created by yinhao on 2017/6/13.
 */
public class ProtoBufTest {
    public static void main(String[] args)throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder().
                setName("zhangsan").setAge(20).setAddress("北京").build();

        byte[] byteArray = student.toByteArray();

        DataInfo.Student student1 = DataInfo.Student.parseFrom(byteArray);

        System.out.println(student1.getName());
        System.out.println(student1.getAge());
        System.out.println(student1.getAddress());
    }
}

package com.shengsiyuan.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * Created by yinhao on 2017/7/27.
 */
public class NIOTest4 {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);

        System.out.println("capacity:" + intBuffer.capacity());

        for (int i = 0;i < 5;i++){
            int randomNumber = new SecureRandom().nextInt(20);
            intBuffer.put(randomNumber);//写入到Buffer
        }

        System.out.println("before flip position:" + intBuffer.position());

        System.out.println("before flip limit:" + intBuffer.limit());

        intBuffer.flip();//Buffer角色转换

        System.out.println("after flip position:" + intBuffer.position());

        System.out.println("after flip limit:" + intBuffer.limit());

        while (intBuffer.hasRemaining()){
            System.out.println("position:" + intBuffer.position());
            System.out.println("limit:" + intBuffer.limit());
            System.out.println("capacity:" + intBuffer.capacity());
            System.out.println(intBuffer.get());//从Buffer中读
        }

        System.out.println("again before flip position:" + intBuffer.position());

        System.out.println("again before flip limit:" + intBuffer.limit());

        intBuffer.flip();//Buffer角色转换

        System.out.println("again after flip position:" + intBuffer.position());

        System.out.println("again after flip limit:" + intBuffer.limit());
    }
}

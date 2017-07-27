package com.shengsiyuan.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * Created by yinhao on 2017/7/27.
 */
public class NIOTest {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);

        for (int i = 0;i < intBuffer.capacity();i++){
            int randomNumber = new SecureRandom().nextInt(20);
            intBuffer.put(randomNumber);//写入到Buffer
        }

        intBuffer.flip();//Buffer角色转换

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());//从Buffer中读
        }
    }
}

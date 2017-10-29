package com.shengsiyuan.nio;

import java.nio.ByteBuffer;

/**
 * Created by yinhao on 17/10/29.
 * 只读Buffer，我们可以随时将一个普通的Bufferd调用asReadOnlyBuffer方法返回一个只读Buffer
 * 但不能将一个只读Buffer转换为读写Buffer
 */
public class NIOTest8 {
    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        System.out.println(byteBuffer.getClass());

        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i );
        }

        ByteBuffer readOnlyByteBuffer  = byteBuffer.asReadOnlyBuffer();

        System.out.println(readOnlyByteBuffer.getClass());

        readOnlyByteBuffer.position(0);

        readOnlyByteBuffer.put((byte) 2);

    }
}

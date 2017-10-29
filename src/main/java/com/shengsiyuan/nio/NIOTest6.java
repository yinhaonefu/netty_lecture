package com.shengsiyuan.nio;

import java.nio.ByteBuffer;

/**
 * Created by yinhao on 17/10/29.
 * ByteBuffer中既可以存储byte类型数据，也可以存储其他原生类型数据
 * 并且严格按照存入的顺序取出，因为每种类型占据的长度不一样
 */
public class NIOTest6 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.putInt(10);
        byteBuffer.putChar('a');
        byteBuffer.putDouble(3.14);
        byteBuffer.putLong(10000L);

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getLong());
    }
}

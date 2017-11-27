package com.shengsiyuan.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufTest2 {
    public static void main(String[] args) {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();//复合ByteBuf

        ByteBuf heapBuf = Unpooled.buffer(10);
        ByteBuf directBuf = Unpooled.directBuffer(8);

        compositeByteBuf.addComponent(heapBuf);//添加一个堆内ByteBuf
        compositeByteBuf.addComponent(directBuf);//添加一个堆外直接ByteBuf

        compositeByteBuf.forEach(System.out::println);//遍历每一个ByteBuf

    }
}

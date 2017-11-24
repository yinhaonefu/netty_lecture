package com.shengsiyuan.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(10);
        for (int i = 0;i < 10;i++){
            byteBuf.writeByte(i);//writerIndex增加
        }
        for (int i = 0;i < byteBuf.capacity();i++){
            System.out.println(byteBuf.getByte(i));//readerIndex不会增加
        }
        for (int i = 0;i < byteBuf.capacity();i++){
            System.out.println(byteBuf.readByte(i));//readerIndex增加
        }
    }
}

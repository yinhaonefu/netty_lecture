package com.shengsiyuan.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class ByteBufTest1 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("张hello world", Charset.forName("utf-8"));
        System.out.println(byteBuf);
        if (byteBuf.hasArray()){//堆内缓冲
            byte[] content = byteBuf.array();
            System.out.println(new String(content,Charset.forName("utf-8")));
        }

        System.out.println(byteBuf.arrayOffset());//第一个元素的偏移量
        System.out.println(byteBuf.readerIndex());//读索引
        System.out.println(byteBuf.writerIndex());//写索引
        System.out.println(byteBuf.capacity());//容量


        for (int i = 0;i < byteBuf.readableBytes();i++){
            System.out.println((char)byteBuf.getByte(i));
        }

        System.out.println(byteBuf.getCharSequence(0,3,Charset.forName("utf-8")));//输出张 utf-8字符集 中文占3个字节
    }
}

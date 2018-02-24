package com.shengsiyuan.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest5 {
    public static void main(String[] args)throws Exception {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true){

            //如果把这行注释掉，观察执行效果，加深理解flip和clear方法的原理
            //如果去掉这行代码，buffer不会还原。会不断的重复往output.txt中写入第一次读取到的内容
            buffer.clear();

            int read = inputChannel.read(buffer);

            System.out.println("read:" +read);

            if(-1 == read){
                break;
            }

            buffer.flip();

            outputChannel.write(buffer);
        }

        inputChannel.close();
        outputChannel.close();

    }
}

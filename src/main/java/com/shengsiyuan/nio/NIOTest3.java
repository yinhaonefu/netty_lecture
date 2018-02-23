package com.shengsiyuan.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yinhao on 2017/7/27.
 * IO输出流转换成NIO将数据写到文件中
 */
public class NIOTest3 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] messages = "hello world".getBytes();

        for (int i = 0;i < messages.length;i++){
            byteBuffer.put(messages[i]);
        }

        byteBuffer.flip();

        fileChannel.write(byteBuffer);//从Buffer中读，写到NioTest3.txt中

        fileOutputStream.close();
    }
}

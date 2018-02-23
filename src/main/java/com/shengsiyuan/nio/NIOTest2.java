package com.shengsiyuan.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yinhao on 2017/7/27.
 * IO输入流转换成NIO读取数据输出到控制台
 */
public class NIOTest2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        FileChannel fileChannel =fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);//将Channel中的数据写入到Buffer

        byteBuffer.flip();//角色转换成读Buffer

        while (byteBuffer.remaining() > 0){
            byte b = byteBuffer.get();
            System.out.println("Charactor:" + (char)b);
        }

        fileInputStream.close();
    }
}

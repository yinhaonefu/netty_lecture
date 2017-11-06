package com.shengsiyuan.nio.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 使用NI向服务器端发送数据，零拷贝机制
 */
public class NewIOClient {
    public static void main(String[] args)throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8899));
        socketChannel.configureBlocking(true);

        String fileName = "E:/视频/圣思园/Netty/49_零拷贝深入剖析及用户空间与内核空间切换方式.mp4";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();

        long transfer = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数:" + transfer + ",耗时:" + (System.currentTimeMillis() - startTime));

        socketChannel.close();
    }
}

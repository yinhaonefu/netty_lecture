package com.shengsiyuan.nio.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 使用NIO方式读取客户端发送的数据，使用一个channel接收数据，省略了selector组件
 */
public class NewIOServer {
    public static void main(String[] args)throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);//在连接关闭后TIME_WAIT阶段可以重用该连接，见方法文档
        serverSocket.bind(inetSocketAddress);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);//没用使用Selector，不需要非阻塞

            int readCount = 0;

            while (-1 != readCount){
                try {
                    readCount = socketChannel.read(byteBuffer);
                }catch (Exception e){
                    e.printStackTrace();
                }
                byteBuffer.rewind();
            }
        }
    }
}

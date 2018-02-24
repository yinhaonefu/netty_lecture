package com.shengsiyuan.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Created by yinhao on 17/10/30.
 * 关于Buffer的Scattering和Gathering
 *
 * 一个Channel中不仅可以使用一个Buffer，同时可以使用一个Buffer数组
 *
 * Scattering:首先会将第一个Buffer读满，然后再向下一个Buffer中读，如果前一个没读满不会向下一个Buffer中读
 * Gathering:从Buffer中向外写数据时，首先会将第一个Buffer中的数据写完，才会写下一个Buffer中的数据
 *
 * 应用场景
 * 如果自定义协议，可以将header中数据放到第一个Buffer中，将body放到第一个Buffer中，很自然的将报文各部分隔离
 *
 * 运行下面程序，然后可以通过telnet localhost 8899 或者 nc localhost 8899 （mac自带）
 * 输入内容查看输出，只有全部的Buffer都读满时，才会回写所有读到的内容
 */
public class NIOTest11 {
    public static void main(String[] args)throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(inetSocketAddress);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();//等待客户端连接

        while (true){
            int bytesRead = 0;

            while (bytesRead < messageLength){
                long r = socketChannel.read(buffers);//等待客户端输入
                bytesRead += r;
                System.out.println("bytesRead:"+bytesRead);
                Arrays.asList(buffers).stream().
                        map(byteBuffer -> "position:"+byteBuffer.position()+
                        ",limit:"+byteBuffer.limit()).forEach(System.out::println);
            }

            Arrays.asList(buffers).forEach(byteBuffer -> byteBuffer.flip());

            long bytesWriten = 0;

            while (bytesWriten < messageLength) {
                long r = socketChannel.write(buffers);//将客户端的输入回写给客户端
                bytesWriten += r;
            }

            Arrays.asList(buffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("bytesRead: " + bytesRead + ", bytesWriten: " + bytesWriten + ", messageLength: " + messageLength);
        }

    }
}

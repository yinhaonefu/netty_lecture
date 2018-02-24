package com.shengsiyuan.nio.lesson43;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by yinhao on 17/10/31.
 * 启动程序，在多个终端分别使用nc 或者telnet localhost 8899 连接 输入内容 查看输出
 */
public class NIioServer {

    public static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args)throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            try {
                selector.select();//阻塞等待关注的事件发生，直到关注的任一事件发生才会继续执行，返回结果是关注的事件数量
                Set<SelectionKey> selectionKeys = selector.selectedKeys();//获取所有关注的事件

                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        if(selectionKey.isAcceptable()){
                            //因为注册到selector上监听连接的channel是ServerSocketChannel，所以强制类型转换不会出错
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();//获取当前连接进来客户端的channel
                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);

                            String key = "["+ UUID.randomUUID()+"]";
                            clientMap.put(key,client);

                        }else if (selectionKey.isReadable()){
                            client = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int count = client.read(byteBuffer);
                            if (count > 0){
                                byteBuffer.flip();

                                Charset charset = Charset.forName("utf-8");
                                String receiveMessage = String.valueOf(charset.decode(byteBuffer).array());
                                System.out.println(client+":"+receiveMessage);

                                //获取发送消息段的channel对应的key，用于通知其他channel是谁发的消息
                                String senderKey = null;
                                for (Map.Entry<String,SocketChannel> entry : clientMap.entrySet()){
                                    if (client == entry.getValue()){
                                        senderKey = entry.getKey();
                                        break;
                                    }
                                }

                                //向每一个注册的的channel发送接收到的内容以及，该内容的发送者
                                for (Map.Entry<String,SocketChannel> entry : clientMap.entrySet()){
                                    SocketChannel value = entry.getValue();
                                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                                    buffer.put((senderKey+":"+receiveMessage).getBytes());
                                    buffer.flip();
                                    value.write(buffer);
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });

                selectionKeys.clear();//不删除之前注册的channel会引起空指针异常
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

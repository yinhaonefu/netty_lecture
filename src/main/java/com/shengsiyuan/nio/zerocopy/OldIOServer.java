package com.shengsiyuan.nio.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 从客户端读取文件内容
 */
public class OldIOServer {
    public static void main(String[] args)throws Exception {
        ServerSocket serverSocket = new ServerSocket(8899);

        while (true){
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            try {
                byte[] byteArray = new byte[4096];
                while (true){
                    int read = dataInputStream.read(byteArray, 0, byteArray.length);
                    if (-1 == read){
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

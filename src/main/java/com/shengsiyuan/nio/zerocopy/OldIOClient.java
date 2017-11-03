package com.shengsiyuan.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

/**
 * 读取磁盘上的文件向服务端发送数据，查看执行时间
 */
public class OldIOClient {
    public static void main(String[] args)throws Exception {
        Socket socket = new Socket("localhost",8899);
        String fileName = "E:/视频/圣思园/Netty/49_零拷贝深入剖析及用户空间与内核空间切换方式.mp4";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] byteArray = new byte[4096];
        long read;
        long total = 0;
        long startTime = System.currentTimeMillis();

        while ((read = fileInputStream.read(byteArray)) >= 0){
            total += read;
            dataOutputStream.write(byteArray);
        }

        System.out.println("发送总字节数:"+total+",耗时:"+ (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        fileInputStream.close();
        socket.close();

    }
}

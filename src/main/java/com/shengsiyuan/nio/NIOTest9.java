package com.shengsiyuan.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yinhao on 17/10/30.
 * 通过内存映射MappedByteBuffer可以直接在操作系统内存中修改文件
 * 执行后使用文件编辑器打开NioTest9.txt发现内容已经发生变化
 */
public class NIOTest9 {
    public static void main(String[] args)throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        //映射文件为读写模式，并映射从第0个位置开始一共5个字节的内容到操作系统内存中
        //直接在操作系统内存中修改对应位置的字节，文件内容也发生变化
        //idea打开看不出变化，需要到文件目录中单独打开查看
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);
        mappedByteBuffer.put(0,(byte)'a');
        mappedByteBuffer.put(3,(byte)'b');
        randomAccessFile.close();
    }
}

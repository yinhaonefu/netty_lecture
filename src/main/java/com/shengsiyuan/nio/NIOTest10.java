package com.shengsiyuan.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by yinhao on 17/10/30.
 * 对文件操作时可以对文件指定范围进行上锁
 */
public class NIOTest10 {
    public static void main(String[] args)throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        //加锁操作，指定起始位置和锁定的文件长度以及是共享锁还是排它锁
        FileLock fileLock = fileChannel.lock(3,6,true);

        System.out.println("valid:" + fileLock.isValid());
        System.out.println("lock type:" + fileLock.isShared());

        fileLock.release();
        randomAccessFile.close();
    }
}

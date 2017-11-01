package com.shengsiyuan.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Java 编解码
 */
public class NIOTest13 {
    public static void main(String[] args)throws Exception {
        String inputFile = "NIOTest13_In.txt";
        String outputFile = "NIOTest13_Out.txt";

        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile,"r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile,"rw");

        long length = new File(inputFile).length();

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        //读到操作系统内存中
        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = decoder.decode(inputData);//将读取的字节内容解码成字符
        ByteBuffer byteBuffer = encoder.encode(charBuffer);//再将字符编码成字节

        outputFileChannel.write(byteBuffer);//将编码后的字节写到输出文件中

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();
    }
}

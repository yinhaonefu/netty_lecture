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
        //系统支持的字符集
        Charset.availableCharsets().forEach((k,v) -> {
            System.out.println(k + " " + v);
        });

        String inputFile = "NIOTest13_In.txt";
        String outputFile = "NIOTest13_Out.txt";

        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile,"r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile,"rw");

        long length = new File(inputFile).length();

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        //读到操作系统内存中enc
        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = decoder.decode(inputData);//将读取的字节内容解码成字符
        ByteBuffer byteBuffer = encoder.encode(charBuffer);//再将字符编码成字节

        System.out.println("---");
        System.out.println(charBuffer.get(0));
        System.out.println(charBuffer.get(1));
        System.out.println(charBuffer.get(2));
        System.out.println(charBuffer.get(3));
        System.out.println(charBuffer.get(4));
        System.out.println(charBuffer.get(5));
        System.out.println(charBuffer.get(6));
        System.out.println(charBuffer.get(7));
        System.out.println(charBuffer.get(8));
        System.out.println(charBuffer.get(9));
        System.out.println(charBuffer.get(10));
        System.out.println(charBuffer.get(11));
        System.out.println(charBuffer.get(12));
        System.out.println(charBuffer.get(13));
        System.out.println(charBuffer.get(14));
        System.out.println(charBuffer.get(15));
        System.out.println(charBuffer.get(16));
        System.out.println(charBuffer.get(17));
        System.out.println(charBuffer.get(18));
        System.out.println(charBuffer.get(19));
        System.out.println(charBuffer.get(20));
        System.out.println(charBuffer.get(21));
        System.out.println(charBuffer.get(22));
        System.out.println("---");

        outputFileChannel.write(byteBuffer);//将编码后的字节写到输出文件中

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();


    }
}

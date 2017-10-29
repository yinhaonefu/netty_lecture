package com.shengsiyuan.nio;

import java.nio.ByteBuffer;

/**
 * Created by yinhao on 17/10/29.、
 * sliceBuffer，调用slice方法可以返回一个从position到limit范围的buffer，
 * 返回的buffer和原buffer共享一份数据，发生任何修改对两个buffer都是可见的
 */
public class NIOTest7 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0;i < byteBuffer.capacity();i++){
            byteBuffer.put((byte)i);
        }

        //截取索引位置从2到6的buffer，然后将截取后的buffer每个元素乘以2
        byteBuffer.position(2);
        byteBuffer.limit(6);

        ByteBuffer sliceBuffer = byteBuffer.slice();

        for (int i = 0;i < sliceBuffer.capacity();i++){
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i,b);
        }

        //重新输出原来的buffer，索引位置2到6的元素也改变了
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());

        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
    }
}

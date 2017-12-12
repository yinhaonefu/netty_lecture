package com.shengsiyuan.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by yinhao on 17/12/12.
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode invoked!");
        System.out.println(in.readableBytes());
        if(in.readableBytes() >= 8){//Long类型占8个字符
            out.add(in.readLong());
        }
    }
}

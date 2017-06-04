package com.shengsiyuan.netty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * Created by yinhao on 17/6/4.
 */
public class MySocketServerHandler extends SimpleChannelInboundHandler<String> {//泛型是传输数据的类型
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "," + msg);
        ctx.channel().writeAndFlush("from server: " + UUID.randomUUID());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

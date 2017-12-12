package com.shengsiyuan.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * Created by yinhao on 17/6/4.
 */
public class MySocketClientHandler extends SimpleChannelInboundHandler<Long> {//泛型是传输数据的类型
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "," + msg);
        System.out.println("client output:" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //连接建立好后，客户端先主动发起请求
        ctx.writeAndFlush(123456l);
    }
}

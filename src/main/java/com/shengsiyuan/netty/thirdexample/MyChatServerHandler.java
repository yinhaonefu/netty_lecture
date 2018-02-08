package com.shengsiyuan.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by yinhao on 2017/6/5.
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String>{

    //保存所有与服务端建立好连接的客户端
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if(channel != ch){
                ch.writeAndFlush(channel.remoteAddress() + "发送的消息:" + msg + "\n");
            }else{
                ch.writeAndFlush("自己发送的消息:" + msg + "\n");
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线");
    }

    //有客户端发起连接时会执行
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("【客户端】 - " + channel.remoteAddress() + " 加入\n");
        //channelGroup的writeAndFlush方法会调用其中的每一个channel的writeAndFlush方法
        channelGroup.writeAndFlush("【客户端】 - " + channel.remoteAddress() + " 加入\n");
        channelGroup.add(channel);
    }

    //连接断开回调
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【客户端】 - " + channel.remoteAddress() + " 离开\n");
//        channelGroup.remove(channel);//即使不移除，netty也会自动去移除该连接
        System.out.println(channelGroup.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

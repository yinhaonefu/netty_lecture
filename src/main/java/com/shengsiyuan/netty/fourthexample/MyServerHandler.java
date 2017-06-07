package com.shengsiyuan.netty.fourthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by yinhao on 2017/6/7.
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            String eventType = null;
            switch (event.state()){
                case READER_IDLE:
                    eventType = "读空闲";//超过指定时间未读到客户端发送的信息
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";//超过指定时间未向客户端发送信息
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";//超过指定时间，即没读到客户端发过来的信息，也没向客户端发送信息
                    break;
            }

            System.out.println(ctx.channel().remoteAddress() + "超时事件:" + eventType);
            ctx.channel().close();
        }
    }
}

package com.shengsiyuan.netty.fifthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by yinhao on 2017/6/7.
 */
public class WebSocketChanneInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new HttpServerCodec());

        pipeline.addLast(new ChunkedWriteHandler());
        //对消息进行聚合的处理器，如果请求消息按给定长度被切割成多个段，会将几个段聚合
        pipeline.addLast(new HttpObjectAggregator(8192));
        //WebSocket协议请求处理器 ws://localhost:9999/ws 参数里指定的是后一个ws路径，第一个ws是固定的
        //查看test.html客户端请求代码
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义处理器
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}

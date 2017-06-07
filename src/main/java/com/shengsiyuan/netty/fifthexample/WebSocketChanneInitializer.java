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
        //对消息进行聚合的处理器
        pipeline.addLast(new HttpObjectAggregator(8192));
        //WebSocket协议请求处理器
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义处理器
        pipeline.addLast(null);
    }
}

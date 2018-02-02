package com.shengsiyuan.netty.firstexamle;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created by yinhao on 2017/6/2.
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //HttpServerCodec 对请求进行编码解码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        //TestHttpServerHandler 自定义请求处理器  每次创建新对象，不要单例的
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
    }
}

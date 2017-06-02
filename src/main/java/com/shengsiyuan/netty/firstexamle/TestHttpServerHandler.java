package com.shengsiyuan.netty.firstexamle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * Created by yinhao on 2017/6/2.
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{

    //读取客户端发送的请求，向客户端返回响应
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //响应内容
        ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
        //构造response，并传入响应内容
        FullHttpResponse response = new DefaultFullHttpResponse
                (HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
        ctx.writeAndFlush(response);
    }
}

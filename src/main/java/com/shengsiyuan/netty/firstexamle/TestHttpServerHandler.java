package com.shengsiyuan.netty.firstexamle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * Created by yinhao on 2017/6/2.
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{//泛型是传输数据的类型



    //读取客户端发送的请求，向客户端返回响应
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

//        System.out.println("msg:" + msg.getClass());

        System.out.println(ctx.channel().remoteAddress());

        if(msg instanceof HttpRequest){

            HttpRequest httpRequest = (HttpRequest)msg;

            System.out.println("request method:" + httpRequest.method().name());

            URI uri = new URI(httpRequest.uri());

            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("request " + uri.getPath());
                return;
            }

            //响应内容
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            //构造response，并传入响应内容
            FullHttpResponse response = new DefaultFullHttpResponse
                    (HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            ctx.writeAndFlush(response);//cxt.write()方法只会将响应内容放到缓冲区，并不会返回给客户端

            ctx.channel().close();//服务器主动关闭连接 可以判断是Http1.0还是http1.1来进行连接关闭的时间选择
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }
}

package com.shengsiyuan.netty.firstexamle;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by yinhao on 2017/6/2.
 */
public class TestServer {

    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();//接收客户端连接交给workerGroup处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();//处理客户端连接
        try{       //ServerBootstrap 服务启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).
                    channel(NioServerSocketChannel.class).//通过反射创建NioServerSocketChannel实例
                    childHandler(new TestServerInitializer());//handler 针对bossGroup处理 childHandler 针对workerGroup处理
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();//访问http://localhost:8899
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

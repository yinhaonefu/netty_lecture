package com.shengsiyuan.netty.sixthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created by yinhao on 2017/6/14.
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //添加针对protobuf支持的编解码处理器

        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        //针对生成的MyDataInfo.Person进行解码的处理器
        pipeline.addLast(new ProtobufDecoder(MyDataInfo.Person.getDefaultInstance()));

        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());

        pipeline.addLast(new ProtobufEncoder());

        //添加自定义处理器

        pipeline.addLast(new TestServerHandler());
    }
}

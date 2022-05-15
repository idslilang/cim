package com.crossoverjie.demo.netty.initializer;

import com.crossoverjie.cim.common.protocol.CIMRequestProto;
import com.crossoverjie.demo.netty.handler.AuthHandler;
import com.crossoverjie.demo.netty.handler.NettyServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class NettyServerInitializer extends ChannelInitializer<Channel> {

    private final NettyServerHandler serverHandler = new NettyServerHandler();
    private final AuthHandler authHandler = new AuthHandler();

    @Override
    protected void initChannel(Channel ch) throws Exception {

        ch.pipeline()
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(CIMRequestProto.CIMReqProtocol.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(authHandler)
                .addLast(serverHandler);
    }
}
package com.crossoverjie.demo.netty.initializer;

import com.crossoverjie.cim.common.protocol.CIMResponseProto;
import com.crossoverjie.demo.netty.handler.NettyClientHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */
public class NettyClientInitializer extends ChannelInitializer<Channel> {

    NettyClientHandler handler = new NettyClientHandler();
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                //拆包解码
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(CIMResponseProto.CIMResProtocol.getDefaultInstance()))
                //
                //拆包编码
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(handler)
        ;
    }
}

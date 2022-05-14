package com.crossoverjie.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new StringDecoder());
                    }
                });
        ChannelFuture future = null;
        try {
            future = bootstrap.connect("127.0.0.1", 8000).sync();
        } catch (Exception e) {

        }
        if (future.isSuccess()) {

            System.out.println("启动 client 成功");
        }
        Channel channel = (SocketChannel) future.channel();
        while (true) {
            String msg ="hello word netty";
            ByteBuf message = Unpooled.buffer(msg.getBytes().length);
            message.writeBytes(msg.getBytes());
            ChannelFuture channelFuture = channel.writeAndFlush(message);
            channelFuture.addListener((ChannelFutureListener) channelFuture2 ->
                    System.out.println("客户端手动发消息成功"));
            Thread.sleep(2000);
        }
    }
}

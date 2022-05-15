package com.crossoverjie.demo.netty.client;

import com.crossoverjie.cim.common.constant.Constants;
import com.crossoverjie.cim.common.protocol.CIMRequestProto;
import com.crossoverjie.demo.netty.initializer.NettyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */
public class NettyClient {

    private static SocketChannel channel;

    public static void main(String[] args) throws InterruptedException {

        startClient();
        registToServer(1, "idslilang");
        sendMsg();

    }

    /**
     * 向服务器注册
     */
    private static void registToServer(long userId, String userName) {
        CIMRequestProto.CIMReqProtocol login = CIMRequestProto.CIMReqProtocol.newBuilder()
                .setRequestId(userId)
                .setReqMsg(userName)
                .setType(Constants.CommandType.LOGIN)
                .build();
        ChannelFuture future = channel.writeAndFlush(login);
        future.addListener((ChannelFutureListener) channelFuture ->
                System.out.println("Registry cim server success!")
        );
    }

    private static void sendMsg() throws InterruptedException {

        while (true) {
            CIMRequestProto.CIMReqProtocol login = CIMRequestProto.CIMReqProtocol.newBuilder()
                    .setRequestId(12321L)
                    .setReqMsg("hello world netty !")
                    .setType(Constants.CommandType.LOGIN)
                    .build();
            ChannelFuture channelFuture = channel.writeAndFlush(login);
            channelFuture.addListener((ChannelFutureListener) channelFuture2 ->
                    System.out.println("客户端手动发消息成功"));
            Thread.sleep(2000);
        }
    }

    private static void startClient() {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
        ChannelFuture future = null;
        try {
            future = bootstrap.connect("127.0.0.1", 8000).sync();
        } catch (Exception e) {

        }
        if (future.isSuccess()) {

            System.out.println("启动 client 成功");

            channel = (SocketChannel) future.channel();
        }
    }
}

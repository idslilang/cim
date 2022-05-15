package com.crossoverjie.demo.netty.server;

import com.crossoverjie.demo.netty.initializer.NettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */
public class NettyServer {

    private static EventLoopGroup boss = new NioEventLoopGroup();
    private static EventLoopGroup work = new NioEventLoopGroup();


    public static void main(String[] args) throws InterruptedException {

        startServer(8000);
    }

    public static void startServer(int nettyPort) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(nettyPort))
                //保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new NettyServerInitializer());

        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            System.out.println("Start  server success!!!");
        }
    }
}

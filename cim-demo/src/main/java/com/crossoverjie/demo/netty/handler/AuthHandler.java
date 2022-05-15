package com.crossoverjie.demo.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    private AttributeKey key = AttributeKey.newInstance("login");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (isLogin(ctx.channel())) {
            ctx.pipeline().remove(this);
        } else {
            markAsLogin(ctx.channel());
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("auth hanlder removed");
        super.handlerRemoved(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("auth hanlder add");
        super.handlerAdded(ctx);
    }

    private boolean isLogin(Channel channel) {
        return channel.attr(key) != null;
    }

    private void markAsLogin(Channel channel) {
        System.out.println("auth hanlder markAsLogin execute");
        channel.attr(key).set(true);
    }
}


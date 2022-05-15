package com.crossoverjie.demo.netty.handler;

import com.alibaba.fastjson.JSON;
import com.crossoverjie.cim.common.protocol.CIMRequestProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<CIMRequestProto.CIMReqProtocol> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //客户端和服务端建立连接时调用
        System.out.println("server connect success!");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开了，重新连接！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //异常时断开连接
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CIMRequestProto.CIMReqProtocol cimReqProtocol) throws Exception {

        System.out.println("收到服务器端消息：" + JSON.toJSONString(cimReqProtocol));

    }
}

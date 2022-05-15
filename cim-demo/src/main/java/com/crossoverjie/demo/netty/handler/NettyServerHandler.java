package com.crossoverjie.demo.netty.handler;

import com.crossoverjie.cim.common.exception.CIMException;
import com.crossoverjie.cim.common.protocol.CIMRequestProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<CIMRequestProto.CIMReqProtocol> {

    /**
     * 取消绑定
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //可能出现业务判断离线后再次触发 channelInactive
        ctx.channel().close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CIMRequestProto.CIMReqProtocol msg) throws Exception {
        System.out.println("received msg:"+ msg.toString());

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (CIMException.isResetByPeer(cause.getMessage())) {
            return;
        }
    }
}

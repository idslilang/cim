package com.crossoverjie.demo.netty.utils;

import com.crossoverjie.demo.netty.constant.NettyConstant;
import com.crossoverjie.demo.netty.entity.Packet;
import com.crossoverjie.demo.netty.entity.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */
public class ByteBufUtils {

    public static  ByteBuf encode(Packet packet) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        byteBuf.writeByte(NettyConstant.MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());

        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }


    public static  Packet decode(ByteBuf byteBuf) {
        //跳过魔数
        byteBuf.skipBytes(1);
        //跳过版本号
        byteBuf.skipBytes(1);
        //序列化算法
        byte serializeAlgorithm = byteBuf.readByte();
        //获取指令
        byte commond = byteBuf.readByte();
        //获取数据包长度
        int length = byteBuf.readInt();
        //获取数据结果
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        return Serializer.DEFAULT.deserialize(Packet.class, bytes);
    }
}

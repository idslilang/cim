package com.crossoverjie.demo.netty.entity;

import lombok.Data;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */
@Data
public class LoginResponsePackect extends Packet {
    @Override
    public byte getCommand() {
        return Command.RESPONSE_REQUEST;
    }

    private boolean success;
}

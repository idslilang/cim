package com.crossoverjie.demo.netty.entity;

import lombok.Data;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */

@Data
public class LoginRequestPacket extends Packet {


    private Integer userId;

    private String username;

    private String password;


    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }


}

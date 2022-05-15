package com.crossoverjie.demo.netty.entity;

import lombok.Data;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */

@Data
public abstract class Packet {

    private Byte version = 1;

    public abstract byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}

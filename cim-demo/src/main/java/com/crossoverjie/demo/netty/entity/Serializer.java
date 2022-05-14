package com.crossoverjie.demo.netty.entity;

/**
 * @description:
 * @author: lilang
 * @version:
 * @modified By:1170370113@qq.com
 */
public interface Serializer {

    /**
     * 获取算法
     *
     * @return
     */
    byte getSerializerAlgorithm();


    byte[] serialize(Object object);


    <T> T deserialize(Class<T> tClass, byte[] bytes);


    Serializer DEFAULT = new JsonSerializer();
}

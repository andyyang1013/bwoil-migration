package com.bwoil.c2b.migration.utils;

/**
 * @author jiangshiwen
 * @ClassName Serializable
 * @Description java 实现PHP serialize() unserialize接口
 * @date 2015-6-17 上午11:13:01
 */
interface Serializable {
    byte[] serialize();

    void unserialize(byte[] ss);
}

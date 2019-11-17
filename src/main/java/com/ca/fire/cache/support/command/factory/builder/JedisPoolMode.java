package com.ca.fire.cache.support.command.factory.builder;

/**
 *
 */
public enum JedisPoolMode {
    /**
     * 无密码普通模式
     */
    Normal_WithoutPassword(1),
    /**
     * 有密码普通模式
     */
    Normal_WithPassword(2),
    /**
     * 普通模式(无密码+有密码)
     */
    Normal(3),
    /**
     * 无密码哨兵模式
     */
    Sentinel_WithoutPassword(4),
    /**
     * 有密码哨兵模式
     */
    Sentinel_WithPassword(5),
    /**
     * 哨兵模式(无密码+有密码)
     */
    Sentinel(6),
    /**
     * 混合模式
     */
    Mix(100);

    private int value;

    public int getValue() {
        return value;
    }

    JedisPoolMode(int value) {
        this.value = value;
    }

}

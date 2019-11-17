package com.ca.fire.cache.support.command;

/**
 * @author
 */
public interface CommandExecutor<T> {
    /**
     * 命令执行者
     *
     * @param key  redis键
     * @param args redis需要的参数
     * @return 命令执行后的返回值
     */
    T execute(String key, Object[] args);
}

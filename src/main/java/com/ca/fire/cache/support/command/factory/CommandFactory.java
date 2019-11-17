package com.ca.fire.cache.support.command.factory;


import com.ca.fire.cache.support.command.CommandExecutor;

/**
 * @author
 */
public interface CommandFactory {
    /**
     * 创建命令执行器
     *
     * @param commandName 命令名称
     * @param commandArgs 命令参数
     * @return 命令执行器
     */
    CommandExecutor create(String commandName, Object[] commandArgs);
}

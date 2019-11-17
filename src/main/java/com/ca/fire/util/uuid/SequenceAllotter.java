package com.ca.fire.util.uuid;

/**
 */
public interface SequenceAllotter {
    /**
     * 按照给种子生成编号
     *
     * @param name 编号对应的名称
     * @return 编号
     */
    Long allot(String name);

    /**
     * 按照给种子生成编号
     *
     * @param seed 生成编号的种子
     * @param name 编号对应的名称
     * @return 编号
     */
    Long allot(String seed, String name);
}

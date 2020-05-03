package com.ca.fire.test.code;

import java.io.Serializable;

/**
 * 亚一中件库出库拣货
 * User: zengwu
 * Date: 13-11-7  下午1:37
 * version:1.0
 */
public class TaskBusinessType implements Serializable {
    /**
     * 任务类型
     */
    private String taskTypeNo;

    /**
     * 任务优先级
     */
    private Integer taskTypeLevel;

    public String getTaskTypeNo() {
        return taskTypeNo;
    }

    public void setTaskTypeNo(String taskTypeNo) {
        this.taskTypeNo = taskTypeNo;
    }

    public Integer getTaskTypeLevel() {
        return taskTypeLevel;
    }

    public void setTaskTypeLevel(Integer taskTypeLevel) {
        this.taskTypeLevel = taskTypeLevel;
    }
}

package com.ca.fire.dao;

import com.ca.fire.domain.ScheduleJob;

import java.util.List;

public interface ScheduleJobDao {
    List<ScheduleJob> select(Object o);

    ScheduleJob selectByPrimaryKey(Long jobId);

    void insertSelective(ScheduleJob job);

    void updateByPrimaryKeySelective(ScheduleJob job);
}

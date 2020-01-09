package com.ca.fire.task;

import com.ca.fire.domain.ScheduleJob;
import com.ca.fire.until.JobUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzJobFactory implements Job {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        JobUtils.invokMethod(scheduleJob,context);
    }
}

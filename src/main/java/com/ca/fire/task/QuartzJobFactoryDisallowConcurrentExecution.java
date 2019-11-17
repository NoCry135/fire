package com.ca.fire.task;

import com.ca.fire.domain.ScheduleJob;
import com.ca.fire.util.JobUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzJobFactoryDisallowConcurrentExecution implements Job {
    public final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        JobUtils.invokMethod(scheduleJob, context);
    }
}

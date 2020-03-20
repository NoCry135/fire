package com.ca.fire.test.jdbc1;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2020/1/17
 */
public class ConnectionPoolMonitor {

    private static final int MONITOR_DELAY_SECONDS = 5;
    protected final Logger logger = LoggerFactory.getLogger("DataSourceMonitor");
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, new ThreadFactoryBuilder().setNameFormat("DataSourceMonitor-%d").setDaemon(true).build());
    private int monitorIntervalSeconds = 1;

    public void setMonitorIntervalSeconds(int monitorIntervalSeconds) {
        if (monitorIntervalSeconds <= 0) {
            return;
        }
        this.monitorIntervalSeconds = monitorIntervalSeconds;
    }

    public void start() {
        scheduledExecutorService.scheduleAtFixedRate(new Monitor(), MONITOR_DELAY_SECONDS, monitorIntervalSeconds, TimeUnit.SECONDS);
    }

    class Monitor implements Runnable {

        @Override
        public void run() {
            System.out.println(String.format("currentActive:%1$s,currentIdle:%2$s", 1, 2));

        }
    }

    public static void main(String[] args) {
        new ConnectionPoolMonitor().start();
        Scanner sc = new Scanner(System.in);
    }
}

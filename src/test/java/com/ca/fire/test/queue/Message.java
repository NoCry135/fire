package com.ca.fire.test.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2020/4/29
 */
public class Message implements Delayed {

    private int id;
    private String body; // 消息内容
    private long excuteTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。

    public Message() {
    }

    public Message(int id, String body, long delayTime) {
        this.id = id;
        this.body = body;
        this.excuteTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
        Message msg = (Message) delayed;
        return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1
                : (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1 : 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(long excuteTime) {
        this.excuteTime = excuteTime;
    }
}

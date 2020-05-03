package com.ca.fire.until;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2020/5/3
 */
public class RedisDelayQueue<T> {

    private Jedis jedis;

    private String queueKey;

    private Type taskType = new TypeReference<TaskItem<T>>() {
    }.getType();


    public RedisDelayQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }


    public void delay(T msg) {
        TaskItem<T> taskItem = new TaskItem<>();
        String uuid = UUID.randomUUID().toString();
        taskItem.setId(uuid);
        taskItem.setMsg(msg);
        String json = JSON.toJSONString(taskItem);
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, json);
    }

    public void loop() {
        while ((!Thread.interrupted())) {
            //取一条
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            String next = values.iterator().next();
            if (jedis.zrem(queueKey, next) > 0) {
                //取到数据
                TaskItem<T> task = JSON.parseObject(next, taskType);
                handleMsg(task.getMsg());
            }
        }
    }

    private void handleMsg(T msg) {
        System.out.println(msg);
    }

    static class TaskItem<T> {
        private String id;
        private T msg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public T getMsg() {
            return msg;
        }

        public void setMsg(T msg) {
            this.msg = msg;
        }
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        RedisDelayQueue<String> delayQueue = new RedisDelayQueue<>(jedis, "q-demo");
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                delayQueue.delay("hello world " + i);
            }
        });

        Thread consumer = new Thread(() -> {
            delayQueue.loop();
        });


        producer.start();
        consumer.start();
        try {
            producer.join();
            TimeUnit.SECONDS.sleep(6);
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

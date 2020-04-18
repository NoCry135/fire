package com.ca.fire.test.zookeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created on 2020/4/12
 */
public class TestClient {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("39.100.121.226:2181", 5000);
        System.out.println("ZooKeeper session established.");
    }
}

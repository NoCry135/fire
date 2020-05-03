package com.ca.fire.test.zookeeper;

/**
 *
 */
public interface NodeLifeCycle {
    boolean isExpired(String nodeContentFromServer);

    String getNewNode(String nodeData);
}

package com.ca.fire.test.zookeeper;

/**
 *
 */
public interface SyncLocker {

    boolean tryClaimLock(String nodeName, String nodeData);

    void releaseLockSilence(String nodeName);
}

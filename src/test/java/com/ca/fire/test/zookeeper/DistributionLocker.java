package com.ca.fire.test.zookeeper;

import com.google.common.base.Preconditions;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 *
 */
public class DistributionLocker implements SyncLocker {
    private static final Logger logger = LoggerFactory.getLogger(DistributionLocker.class);
    private static final String PATH_SPLITTER = "/";
    private static final int DEFAULT_SESSION_TIMEOUT = 60000;
    private final String rootPath;
    private final String zkServers;
    private final int sessionTimeout;
    private org.I0Itec.zkclient.ZkClient zkClient;
    private NodeLifeCycle nodeLifeCycle;

    public DistributionLocker(String zkServers, String rootPath) {
        this(zkServers, DEFAULT_SESSION_TIMEOUT, rootPath);
    }

    public DistributionLocker(String zkServers, int sessionTimeout, String rootPath) {
        this.zkServers = zkServers;
        this.sessionTimeout = sessionTimeout;
        this.rootPath = rootPath;
        checkConstructorArgs();
        initZkClient();
        createRootPath();
    }

    public void setNodeLifeCycle(NodeLifeCycle nodeLifeCycle) {
        this.nodeLifeCycle = nodeLifeCycle;
    }

    private void checkConstructorArgs() {
        Preconditions.checkArgument(StringUtils.isNotBlank(rootPath), "rootPath is blank!");
        Preconditions.checkArgument(rootPath.charAt(0) == '/', "root path must be starts with /");
        Preconditions.checkArgument(StringUtils.isNotBlank(zkServers), "zkServers is blank!");
        Preconditions.checkArgument(sessionTimeout > 0 && sessionTimeout <= Integer.MAX_VALUE, "sessionTimeout must between 1 and Integer.MAX_VALUE");
    }

    private void initZkClient() {
        try {
//            zkClient = new org.I0Itec.zkclient.ZkClient(zkServers, sessionTimeout, Integer.MAX_VALUE, new StringSerializer());
        } catch (Exception ex) {
            logger.error("init zkClient error!", ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private void createRootPath() {
        try {
            zkClient.createPersistent(rootPath, true);
        } catch (Exception ex) {
            logger.error("create rootPath error!", ex);
        }
    }

    @Override
    public boolean tryClaimLock(String nodeName, String nodeData) {
        String nodePath = getNodePath(nodeName);
        try {
            logger.info(MessageFormat.format("try create node {0},data {1}", nodePath, nodeData));
            zkClient.createPersistent(nodePath, nodeLifeCycle.getNewNode(nodeData));
            return true;
        } catch (ZkNodeExistsException nodeExists) {
            logger.info(MessageFormat.format("node {0} exists,so check the current locker is expired or not", nodePath));
            String nodeDataFromServer = zkClient.readData(nodePath, true);
            if (nodeDataFromServer == null) {
                logger.info(MessageFormat.format("data from {0} is null,it means lock has been released,do not need claim lock", nodePath));
                return false;
            }
            if (nodeLifeCycle.isExpired(nodeDataFromServer)) {
                logger.info(MessageFormat.format("data {0} from {1} is expired!delete the node and try claim", nodeDataFromServer, nodePath));
                zkClient.delete(nodePath);
                return tryClaimLock(nodeName, nodeData);
            }
        } catch (Exception ex) {
            logger.error("unexpected exception occur when try claim lock!", ex);
            throw new RuntimeException("unexpected exception occur when try claim lock!", ex);
        }
        return false;
    }

    @Override
    public void releaseLockSilence(String nodeName) {
        String nodePath = getNodePath(nodeName);
        try {
            zkClient.delete(nodePath);
        } catch (Exception ex) {
            logger.error(MessageFormat.format("delete node {0} error!", nodePath), ex);
        }
    }

    private String getNodePath(String nodeName) {
        return MessageFormat.format("{0}{1}{2}", rootPath, PATH_SPLITTER, nodeName);
    }

}

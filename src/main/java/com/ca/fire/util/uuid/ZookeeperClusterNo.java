package com.ca.fire.util.uuid;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.UnsupportedEncodingException;

/**
 */
public class ZookeeperClusterNo implements ClusterNo, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperClusterNo.class);
    private static final String defaultPath = "/config/datacenter/groupno";
    private static final int defaultSessionTimeout = 60000;
    private static final int defaultConnectTimeout = 3000;
    private ZkSerializer stringSerializer = new ZkSerializer() {
        @Override
        public byte[] serialize(Object data) throws ZkMarshallingError {
            try {
                return ((String) data).getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new ZkMarshallingError(e);
            }
        }

        @Override
        public Object deserialize(byte[] bytes) throws ZkMarshallingError {
            try {
                return new String(bytes, "UTF-8");
            } catch (final UnsupportedEncodingException e) {
                throw new ZkMarshallingError(e);
            }
        }
    };
    private String path = defaultPath;
    private String zkServers;
    private ZkClient zkClient;
    private int sessionTimeout = defaultSessionTimeout;
    private int connectTimeout = defaultConnectTimeout;
    private ClusterNo clusterNoKeeper;
    private String clusterNo;

    public void setPath(String path) {
        if (StringUtils.isBlank(path)) {
            return;
        }
        this.path = path;
    }

    public void setZkServers(String zkServers) {
        this.zkServers = zkServers;
    }

    public void setSessionTimeout(int sessionTimeout) {
        if (sessionTimeout < 1) {
            return;
        }
        this.sessionTimeout = sessionTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        if (connectTimeout < 1) {
            return;
        }
        this.connectTimeout = connectTimeout;
    }

    public void setClusterNoKeeper(LocalFileClusterNo clusterNoKeeper) {
        this.clusterNoKeeper = clusterNoKeeper;
    }

    @Override
    public String getClusterNo() {
        try {
            zkClient = new ZkClient(zkServers, sessionTimeout, connectTimeout, stringSerializer);
            String content = zkClient.readData(path, true);
            String trimContent = StringUtils.trimToEmpty(content);
            if (!StringUtils.equalsIgnoreCase(trimContent, clusterNo)) {
                clusterNo = trimContent;
                if (clusterNoKeeper != null) {
                    clusterNoKeeper.saveClusterNo(clusterNo);
                }
            }
            return clusterNo;
        } catch (Exception ex) {
            logger.error("Read data from zookeeper error!", ex);
        }finally {
            if(zkClient != null){
                zkClient.close();
            }
        }
        return null;
    }

    @Override
    public void saveClusterNo(String value) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isBlank(zkServers)) {
            throw new IllegalStateException("zkServers is blank!");
        }
        if (StringUtils.isBlank(path)) {
            throw new IllegalStateException("path is blank!");
        }
    }

}

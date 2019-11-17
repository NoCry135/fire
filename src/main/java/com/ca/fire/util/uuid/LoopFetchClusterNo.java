package com.ca.fire.util.uuid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 */
public class LoopFetchClusterNo implements ClusterNo {
    private List<ClusterNo> clusterNoProviders;
    private boolean throwExceptionIfClusterNoNotFound;
    private String clusterNo;

    public void setThrowExceptionIfClusterNoNotFound(boolean throwExceptionIfClusterNoNotFound) {
        this.throwExceptionIfClusterNoNotFound = throwExceptionIfClusterNoNotFound;
    }

    public void setClusterNoProviders(List<ClusterNo> clusterNoProviders) {
        this.clusterNoProviders = clusterNoProviders;
    }

    @Override
    public String getClusterNo() {
        if (StringUtils.isNotBlank(this.clusterNo)) {
            return this.clusterNo;
        }
        if (CollectionUtils.isEmpty(clusterNoProviders)) {
            throw new IllegalStateException("No clusterNoProviders!");
        }
        for (ClusterNo clusterNoProvider : clusterNoProviders) {
            this.clusterNo = clusterNoProvider.getClusterNo();
            if (StringUtils.isNotBlank(this.clusterNo)) {
                return this.clusterNo;
            }
        }
        if (this.throwExceptionIfClusterNoNotFound) {
            throw new IllegalStateException("No any clusterNo found!");
        }
        return null;
    }

    @Override
    public void saveClusterNo(String value) {

    }

}

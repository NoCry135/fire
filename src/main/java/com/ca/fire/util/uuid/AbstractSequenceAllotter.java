package com.ca.fire.util.uuid;

import org.apache.commons.lang3.StringUtils;

/**
 */
public abstract class AbstractSequenceAllotter implements SequenceAllotter {
    public static final String DEFAULT_PREFIX = "SequenceAllot";
    public static final String DEFAULT_SPLITTER = "_";
    private String prefix = DEFAULT_PREFIX;
    private String splitter = DEFAULT_SPLITTER;
    protected String seed;

    public void setPrefix(String prefix) {
        if (StringUtils.isBlank(prefix)) {
            return;
        }
        this.prefix = prefix;
    }

    public void setSplitter(String splitter) {
        if (StringUtils.isBlank(splitter)) {
            return;
        }
        this.splitter = splitter;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    @Override
    public Long allot(String seed, String name) {
        String key = appendPrefix(name);
        Long sequenceNo = get(key);
        if (sequenceNo == null) {
            String seedWithPrefix = appendPrefix(seed);
            sequenceNo = incrAndGet(seedWithPrefix);
            save(key, sequenceNo);
        }
        return sequenceNo;
    }

    @Override
    public Long allot(String name) {
        return allot(seed, name);
    }

    protected abstract Long incrAndGet(String seed);

    protected abstract Long get(String key);

    protected abstract void save(String key, Long value);

    protected String appendPrefix(String value) {
        return StringUtils.join(prefix, splitter, value);
    }

}

package com.ca.fire.cache.support.command.factory.builder;

/**
 *
 */
public class SentinelPoolBuilder extends NormalPoolBuilder {

    public SentinelPoolBuilder(JedisPoolBuilder sentinelNoPasswordPoolBuilder, JedisPoolBuilder sentinelWithPasswordPoolBuilder) {
        super(sentinelNoPasswordPoolBuilder, sentinelWithPasswordPoolBuilder);
    }

    @Override
    protected JedisPoolMode getSupportedMode() {
        return JedisPoolMode.Sentinel;
    }
}

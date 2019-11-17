package com.ca.fire.cache.support.command.factory.builder;

/**
 *
 */
public class MixPoolBuilder extends NormalPoolBuilder {

    public MixPoolBuilder(JedisPoolBuilder normalPoolBuilder, JedisPoolBuilder sentinelPoolBuilder) {
        super(normalPoolBuilder, sentinelPoolBuilder);
    }

    @Override
    protected JedisPoolMode getSupportedMode() {
        return JedisPoolMode.Mix;
    }
}

package com.ca.fire.cache.support.command.factory.builder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StaticJedisPoolBuilderFactory implements JedisPoolBuilderFactory {
    private static final List<JedisPoolBuilder> POOL_BUILDERS = new ArrayList<JedisPoolBuilder>(7);

    static {
        POOL_BUILDERS.add(new NoPasswordPoolBuilder());
        POOL_BUILDERS.add(new WithPasswordPoolBuilder());
        POOL_BUILDERS.add(new SentinelNoPasswordPoolBuilder());
        POOL_BUILDERS.add(new SentinelWithPasswordPoolBuilder());
        POOL_BUILDERS.add(new NormalPoolBuilder(POOL_BUILDERS.get(0), POOL_BUILDERS.get(1)));
        POOL_BUILDERS.add(new SentinelPoolBuilder(POOL_BUILDERS.get(2), POOL_BUILDERS.get(3)));
        POOL_BUILDERS.add(new MixPoolBuilder(POOL_BUILDERS.get(4), POOL_BUILDERS.get(5)));
    }

    @Override
    public List<JedisPoolBuilder> create() {
        return POOL_BUILDERS;
    }
}

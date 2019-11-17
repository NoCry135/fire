package com.ca.fire.cache.support.command.factory.builder;


import com.ca.fire.cache.support.command.factory.JedisConfig;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class NormalPoolBuilder extends AbstractJedisPoolsBuilder implements JedisPoolBuilder {
    private JedisPoolBuilder noPasswordPoolBuilder;
    private JedisPoolBuilder withPasswordPoolBuilder;

    public NormalPoolBuilder(JedisPoolBuilder noPasswordPoolBuilder, JedisPoolBuilder withPasswordPoolBuilder) {
        this.noPasswordPoolBuilder = noPasswordPoolBuilder;
        this.withPasswordPoolBuilder = withPasswordPoolBuilder;
    }

    @Override
    protected JedisPoolMode getSupportedMode() {
        return JedisPoolMode.Normal;
    }

    @Override
    public List<JedisPoolWrapper> build(JedisConfig jedisConfig) {
        List<JedisPoolWrapper> jedisPools = new ArrayList<JedisPoolWrapper>();
        jedisPools.addAll(noPasswordPoolBuilder.build(jedisConfig));
        jedisPools.addAll(withPasswordPoolBuilder.build(jedisConfig));
        return jedisPools;
    }
}

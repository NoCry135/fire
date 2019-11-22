package com.ca.fire.test.collections;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by caian on 2019/11/18
 */
public class TestImm {

    public static void main(String[] args) {

        ImmutableList<Object> keys = ImmutableList.of(StringUtils.join("1213", "_", "222", "_", "4333"));
        System.out.println(keys);
    }
}

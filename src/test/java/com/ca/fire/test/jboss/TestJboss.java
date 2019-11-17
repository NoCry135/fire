package com.ca.fire.test.jboss;

import org.jboss.util.id.GUID;
import org.junit.Test;

public class TestJboss {

    @Test
    public void test01(){
        String string = GUID.asString();
        System.out.println(string);
    }
}

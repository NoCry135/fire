package com.ca.fire.test.date;

import org.junit.Test;

import java.time.LocalDateTime;

public class TestJava8Time {

    @Test
    public void testLocalDateTime(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        String replace = LocalDateTime.now().toString().replace("-", "").replace(":","");
        System.out.println(replace);

    }
}

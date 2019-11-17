package com.ca.fire.test.json;

import com.alibaba.fastjson.JSON;
import com.ca.fire.test.domain.Person;
import com.ca.fire.test.domain.Student;
import org.junit.Test;

public class TestFasonJson {

    @Test
    public void test01(){

        Person person = null;
        System.out.println(JSON.toJSONString(person));

        Student student = JSON.parseObject(JSON.toJSONString(person), Student.class);
        System.out.println(student);
        System.out.println(JSON.parseObject("", Person.class));
    }
}

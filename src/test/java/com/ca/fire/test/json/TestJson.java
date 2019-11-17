package com.ca.fire.test.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestJson {

    public static ObjectMapper mapper = new ObjectMapper();

    static {
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        //序列化的时候序列对象的所有属性
//        Include.ALWAYS  是序列化对像所有属性
//
//        Include.NON_NULL 只有不为null的字段才被序列化
//
//        Include.NON_EMPTY 如果为null或者 空字符串和空集合都不会被序列化
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //反序列化的时候如果多了其他属性,不抛出异常
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //如果是空对象的时候,不抛异常
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testObj() throws JsonGenerationException, JsonMappingException, IOException {
        XwjUser user = new XwjUser(1, "Hello World", new Date());
// 写到文件中
//        mapper.writeValue(new File("D:/test.txt"), user);
        //写到控制台
//        mapper.writeValue(System.out, user);

        String jsonStr = mapper.writeValueAsString(user);
        System.out.println("对象转为字符串：" + jsonStr);
//
        byte[] byteArr = mapper.writeValueAsBytes(user);
        System.out.println("对象转为byte数组：" + byteArr);
//
        XwjUser userDe = mapper.readValue(jsonStr, XwjUser.class);
        System.out.println("json字符串转为对象：" + userDe);
//
        XwjUser useDe2 = mapper.readValue(byteArr, XwjUser.class);
        System.out.println("byte数组转为对象：" + useDe2);
    }

    @Test
    public void testList() throws JsonGenerationException, JsonMappingException, IOException {
        List<XwjUser> userList = new ArrayList<>();
        userList.add(new XwjUser(1, "aaa", new Date()));
        userList.add(new XwjUser(2, "bbb", new Date()));
        userList.add(new XwjUser(3, "ccc", new Date()));
        userList.add(new XwjUser(4, "ddd", new Date()));

        String jsonStr = mapper.writeValueAsString(userList);
        System.out.println("集合转为字符串：" + jsonStr);

        List<XwjUser> userListDes = mapper.readValue(jsonStr, List.class);
        System.out.println("字符串转集合：" + userListDes);
    }

    @Test
    public void testMap() {
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("name", "merry");
        testMap.put("age", 30);
        testMap.put("date", new Date());
        testMap.put("user", new XwjUser(1, "Hello World", new Date()));

        try {
            String jsonStr = mapper.writeValueAsString(testMap);
            System.out.println("Map转为字符串：" + jsonStr);
            try {
                Map<String, Object> testMapDes = mapper.readValue(jsonStr, Map.class);
                System.out.println("字符串转Map：" + testMapDes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOther() throws IOException {
        // 修改时间格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        XwjUser user = new XwjUser(1, "Hello World", new Date());
//        user.setIntList(Arrays.asList(1, 2, 3));

        String jsonStr = mapper.writeValueAsString(user);
        System.out.println("对象转为字符串：" + jsonStr);
    }

    @Test
    public void testP1() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //序列化的时候序列对象的所有属性 ALWAYS,不为null:NON_NULL,NON_DEFAULT:不等于默认值,NON_EMPTY:不为空串
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        XwjUser person = new XwjUser(2, "", new Date());
        //这是最简单的一个例子,把一个对象转换为json字符串
        String personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);

        //默认为true,会显示时间戳
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);
    }

    @Test
    public void testP2() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //如果是空对象的时候,不抛异常,也就是对应的属性没有get方法
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        XwjUser person = new XwjUser(1, "zxc", new Date());
        String personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);

        //默认是true,即会抛异常

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);

    }
    @Test
    public void testP3() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        XwjUser person1 = new XwjUser(1, "zxc", new Date());

		String personJson = objectMapper.writeValueAsString(person1);
		System.out.println(personJson);

        //注意,age属性是不存在在person对象中的
        String personStr = "{\"id\":1,\"message\":\"zxc\",\"age\":\"zxc\"}";

        XwjUser person = objectMapper.readValue(personStr, XwjUser.class);
        System.out.println(person);

        //默认为true
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        person = objectMapper.readValue(personStr, XwjUser.class);
        System.out.println(person);

    }
    @Test
    public void testP4() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);

        XwjUser person1 = new XwjUser(1, "zxc", new Date());
        XwjUser person2 = new XwjUser(2, "ldh", new Date());

        List<XwjUser> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);

        //先转换为json字符串
        String personStr = objectMapper.writeValueAsString(persons);

        //反序列化为List<user> 集合,1需要通过 TypeReference 来具体传递值
        List<XwjUser> persons2 = objectMapper.readValue(personStr, new TypeReference<List<XwjUser>>(){});

        for(XwjUser person : persons2) {
            System.out.println(person);
        }

        //2,通过 JavaType 来进行处理返回
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, XwjUser.class);
        List<XwjUser> persons3 = objectMapper.readValue(personStr, javaType);

        for(XwjUser person : persons3) {
            System.out.println(person);
        }

    }
    @Test
    public void testP5() throws IOException {

        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("4444");

        ObjectMapper objectMapper = new ObjectMapper();


        //先转换为json字符串
        String personStr = objectMapper.writeValueAsString(list);
        System.out.println(personStr);



    }
}

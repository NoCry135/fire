package com.ca.fire.rpc;

import com.alibaba.fastjson.JSON;
import com.ca.fire.domain.Employee;
import com.ca.fire.util.HttpClientUtil;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import retrofit2.Call;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath*:spring/spring-config.xml"})
public class TestEmployee {

    @Resource
    private EmployeeRpcService employeeRpcService;

    @Resource
    private HttpClientUtil httpClientUtil;

    @Test
    public void test01() {
        Call<Object> employeeById = employeeRpcService.getEmployeeById(1L);
        Object body = null;
        try {
            body = employeeById.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(body));
    }

    @Test
    public void test03() {
        Call<Object> employeeById = employeeRpcService.testRest(new Employee());
        Object body = null;
        try {
            body = employeeById.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(JSON.toJSONString(body));
    }


    @Test
    public void test02() {
        Map<String, Object> map = new HashedMap();
        map.put("id", 1L);

        Object result = httpClientUtil.getResult("/rest/test", map);
        System.out.println(JSON.toJSONString(result));
    }

}

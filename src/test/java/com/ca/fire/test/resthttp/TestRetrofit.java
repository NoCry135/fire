package com.ca.fire.test.resthttp;

import com.alibaba.fastjson.JSON;
import com.ca.fire.rpc.EmployeeService;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;

public class TestRetrofit {
    @Test
    public void test01() {
        Retrofit builder = new Retrofit.Builder().baseUrl("http://localhost:8180/").build();
        EmployeeService employeeService = builder.create(EmployeeService.class);
//        Call<Object> employee = employeeService.getEmployee(1);
        Call<Object> query = employeeService.query(1);
        try {
            Object body = query.execute().body();
            System.out.println(JSON.toJSON(body));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

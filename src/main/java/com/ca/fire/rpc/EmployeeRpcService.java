package com.ca.fire.rpc;

import com.ca.fire.domain.Employee;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EmployeeRpcService {

    @GET("/employee/getEmployee")
    Call<Object> getEmployeeById(@Query("id") Long id);

    @POST("/rest/test")
    Call<Object> testRest(@Body Employee employee);
}

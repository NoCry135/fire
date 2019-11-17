package com.ca.fire.rpc;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeService {
    @GET("/employee/getEmployee/{id}")
    Call<Object> getEmployee(@Path("id") int id);


    @POST("/service/move/query")
    Call<Object> query(@Body int id);
}

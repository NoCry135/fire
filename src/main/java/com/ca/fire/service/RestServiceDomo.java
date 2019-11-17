package com.ca.fire.service;

import com.ca.fire.domain.Employee;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Service
@Path("/rest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestServiceDomo {

    @POST
    @Path("/test")
    @Consumes(MediaType.APPLICATION_JSON)
    public Object test(Employee request) {
        request.setEmpNo(100);
        request.setEmpName("张三");
        request.setEmail("13966666@qq.com");
        return request;
    }

}

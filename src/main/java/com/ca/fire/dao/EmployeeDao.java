package com.ca.fire.dao;

import com.ca.fire.domain.DeptMent;
import com.ca.fire.domain.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> selectList(Employee employee);

    List<Employee> selectList();

    List<Employee> selectWithAddressList(Employee employee);

    DeptMent selectDept(Employee employee);

    DeptMent selectDeptWithEmpInfo(Employee employee);

}

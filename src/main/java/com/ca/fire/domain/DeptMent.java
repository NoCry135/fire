package com.ca.fire.domain;

import java.util.List;

public class DeptMent extends BaseDomain {

    private Integer deptNo;

    private String deptName;

    private Integer deptLeaderNo;

    private List<Employee> employeeList;

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptLeaderNo() {
        return deptLeaderNo;
    }

    public void setDeptLeaderNo(Integer deptLeaderNo) {
        this.deptLeaderNo = deptLeaderNo;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return super.toString() + "DeptMent{" +
                "deptNo=" + deptNo +
                ", deptName='" + deptName + '\'' +
                ", deptLeaderNo=" + deptLeaderNo +
                ", employeeList=" + employeeList +
                '}';
    }
}

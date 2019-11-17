package com.ca.fire.domain;

import java.util.List;

public class Employee extends BaseDomain {

    private Integer empNo;

    private Integer gender;

    private String email;

    private Integer phone;

    private String empName;

    private Integer deptNo;

    private Address address;

    private List<Car> carList;

    public Employee() {
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", empName=" + empName +
                ", deptNo=" + deptNo +
                ", address=" + address +
                '}';
    }
}

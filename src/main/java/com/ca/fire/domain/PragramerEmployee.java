package com.ca.fire.domain;

public class PragramerEmployee extends Employee {

    private Integer level;

    public PragramerEmployee(String empName, Integer level) {
        super(empName);
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}

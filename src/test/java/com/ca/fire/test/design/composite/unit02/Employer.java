package com.ca.fire.test.design.composite.unit02;

import java.util.List;

public abstract class Employer {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract void add(Employer employer);

    public abstract void delete(Employer employer);

    public List<Employer> employers;

    public void printInfo() {
        System.out.println(name);
    }

    public List getEmployers() {
        return this.employers;
    }

}

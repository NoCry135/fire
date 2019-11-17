package com.ca.fire.test.design.composite.unit02;

import java.util.ArrayList;

/**
 * 将对象以树形结构组织起来,以达成“部分－整体” 的层次结构，使得客户端对单个对象和
 * 组合对象的使用具有一致性.
 */
public class ProjectManager extends Employer {

    public ProjectManager(String name) {
        setName(name);
        employers = new ArrayList();

    }

    @Override
    public void add(Employer employer) {
        employers.add(employer);
    }

    @Override
    public void delete(Employer employer) {
        employers.remove(employer);
    }
}

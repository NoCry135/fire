package com.ca.fire.test.json;

import com.alibaba.fastjson.JSON;
import com.ca.fire.test.domain.Employee;
import com.ca.fire.test.domain.Person;
import com.ca.fire.test.domain.Student;
import com.ca.fire.util.BeanCopierUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class TestCopyBean {

    @Test
    public void test01() {
        List<Employee> employees = getEmps();
        long st1 = System.currentTimeMillis();
        List<Person> people = new ArrayList<>();
        for (Employee employee : employees) {
            Person person = new Person();
            BeanUtils.copyProperties(employee, person);
            people.add(person);
        }
        long st2 = System.currentTimeMillis();
        List<Person> personList = new ArrayList<>();
        for (Employee employee : employees) {
            String o = JSON.toJSONString(employee);
            Person person = JSON.parseObject(o, Person.class);
            personList.add(person);
        }
        long st3 = System.currentTimeMillis();
        System.out.println("BeanUtils.copyProperties(employee, person) time:" + (st2 - st1));
        System.out.println("Person person = JSON.parseObject(o, Person.class) time:" + (st3 - st2));
    }


    @Test
    public void test02() {
        Employee employee = new Employee("张三", 26, 3000D);
        Student student = BeanCopierUtils.copy(employee, Student.class);
        System.out.println(student);
    }

    @Test
    public void test03() {
        List<Employee> emps = getEmps();
//        emps.stream().map((e)->
//            e.getName()
//        ).collect(Collectors.toList()).stream().

    }


    private List<Employee> getEmps() {
        Employee employee1 = new Employee("张三", 26, 3000D);
        Employee employee2 = new Employee("李四", 28, 4000D);
        Employee employee3 = new Employee("王五", 29, 5000D);
        Employee employee4 = new Employee("赵六", 25, 6000D);
        Employee employee5 = new Employee("田七", 23, 7000D);
        Employee employee6 = new Employee("胡巴", 24, 7000D);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
        return employees;
    }


}

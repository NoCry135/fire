package com.ca.fire.dao;

import com.ca.fire.domain.DeptMent;
import com.ca.fire.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/spring-config.xml"})
public class TestEmpDao {


    @Resource
    private EmployeeDao employeeDao;

    @Test
    public void test01() {
        Employee employee = new Employee("2");
        employee.setEmpNo(10004);
        List<Employee> employees = employeeDao.selectList(employee);
        //  没有使用地址时只查询第一条SQL
        System.out.println(employees.get(0).getEmpName());
        try {
            Thread.sleep(1000);
            System.out.println("我醒了!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//           <setting name="lazyLoadingEnabled" value="true"/>
//       <setting name="aggressiveLazyLoading" value="false"/>
//                如果是多表分步查询,使用懒加载时,需要依赖cglib-2.2.2.jar(依赖asm-3.3.1.jar)
        System.out.println(employees.get(0).getAddress().getAddress());
//        employees.forEach(System.out::println);
    }


    @Test
    public void test02() {
        Employee employee = new Employee("1");
        employee.setEmpNo(10004);
//        employee.setDeptNo(1002);
        List<Employee> employees = employeeDao.selectWithAddressList(employee);
        employees.forEach(System.out::println);
    }

    @Test
    public void test03() {
        Employee employee = new Employee("1");
        employee.setDeptNo(1002);
        DeptMent deptMent = employeeDao.selectDept(employee);
        System.out.println(deptMent);
    }

    @Test
    public void test04() {
        Employee employee = new Employee("2");
        employee.setDeptNo(1002);
        DeptMent deptMent = employeeDao.selectDeptWithEmpInfo(employee);
        System.out.println(deptMent);
    }

}

package com.ca.fire.test.io;

import com.ca.fire.test.domain.Employee;

import java.io.*;
import java.util.Arrays;

public class TestObjectStream {
    public static void main(String[] args) throws Exception {
        save("object.txt");//打开文件，人看不懂，因为是给机器看的
        read("object.txt");
    }

    public static void read(String filePath) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filePath)));

        Object readObject = ois.readObject();
        System.out.println(readObject);
        if (readObject instanceof Employee) {
            Employee emp = (Employee) readObject;
            String name = emp.getName();
            double salary = emp.getSalary();
            System.out.println(name + "," + salary);
        }

        int[] arr = (int[]) ois.readObject();
        System.out.println(Arrays.toString(arr));

        ois.close();
    }

    public static void save(String filePath) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)));

        oos.writeObject(new Employee("张三", 20, 80.00));
        int[] arr = {1, 2, 3, 4, 5};
        oos.writeObject(arr);

        oos.close();
    }

}

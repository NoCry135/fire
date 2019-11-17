package com.ca.fire.test.java8.stream;

import com.ca.fire.test.domain.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 的三个步骤
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作
 */
public class TestStreamAPI01 {
    @Test
    public void test() {
        //可以通过Collection系列集合提供的stram()货parallelStream()创建stream
        List<String> list = Arrays.asList("高晓松", "黄飞鸿", "朱元璋");
        Stream<String> stream = list.stream();

        //用过Arrays中的静态方法stream()获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(emps);

        //通过stream类中的静态方法 of()
        Stream<String> stream2 = Stream.of("a", "b", "v");

        //创建无限流
        Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2);
        stream3.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random() * 100).limit(10).forEach(System.out::println);
    }


    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("张三", 18, 333.00),
            new Employee("张三", 28, 333.00),
            new Employee("李四", 58, 5555.55),
            new Employee("王五", 26, 3333.33),
            new Employee("赵六", 36, 6666.66),
            new Employee("田七", 12, 8888.88),
            new Employee("胡三", 32, 8888.88),
            new Employee("田爸", 42, 8888.88)
    );

    @Test
    public void testMatch() {
        employees.stream().allMatch(e -> {
            System.out.println(e.getName());
            return e.getAge().equals(58);
        });
    }

    @Test
    public void testGroupBy() {
        Map<String, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(employees -> employees.getName() + "_" + employees.getAge()));
        Set<Map.Entry<String, List<Employee>>> entries = collect.entrySet();
        for (Map.Entry<String, List<Employee>> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    private List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

    /**
     * 排序
     * sorted() 自然排序(Comparable)
     * sorted(Comparator com) 定制排序
     */
    @Test
    public void testSort() {

        //自然排序
        list.stream().sorted().forEach(System.out::println);

        //定制排序
        employees.stream().sorted((e1, e2) -> {
            if (e1.getAge().equals(e2.getAge())) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }

    /**
     * map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void testMap() {
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        employees.stream().map(Employee::getAge).sorted().forEach(System.out::println);
    }

    /**
     * 筛选与切片
     * filter——接收 Lambda ， 从流中排除某些元素。
     * limit——截断流，使其元素不超过给定数量。
     * skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */
    @Test
    public void testFilter() {
        employees.stream().filter(e -> e.getAge() > 20).forEach(System.out::println);
        employees.stream().filter(e -> e.getAge() > 20).skip(2).forEach(System.out::println);
    }

    @Test
    public void test01() {
        List<String> list1 = Arrays.asList("1", "2", "3", "4");
        List<String> list2 = Arrays.asList("71", "7", "5");
        List<String> collect = list2.stream().filter((e) ->
                list1.contains(e)
        ).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

    @Test
    public void test02() {
        employees.forEach(System.out::println);
        List<Employee> collect = employees.stream().map(e -> {
                    if (e.getAge() > 30) {
                        e.setAge(e.getAge() + 10);
                    } else {
                        e.setAge(e.getAge() - 10);

                    }
                    return e;
                }
        ).collect(Collectors.toList());

        collect.forEach(System.out::println);
        employees.stream().map(e -> {
                    if (e.getAge() > 30) {
                        e.setAge(e.getAge() + 10);
                    } else {
                        e.setAge(e.getAge() - 10);
                    }
                    return e;
                }
        );
        employees.forEach(System.out::println);


    }
}

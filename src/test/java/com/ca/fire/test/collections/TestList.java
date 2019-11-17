package com.ca.fire.test.collections;

import com.alibaba.fastjson.JSON;
import com.ca.fire.domain.bean.User;
import com.ca.fire.test.domain.Book;
import com.ca.fire.test.domain.Person;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestList {
    @Test
    public void test08() {
        List<String> list1 = new ArrayList<>();
        list1.add("张三");
        list1.add("赵四");
        list1.add("五万");
        list1.add("ww");
        list1.add("你妹");

        for (String s : list1) {

            if (!s.equals("ww")) {
                System.out.println(s);

                continue;
            } else {
                System.out.println(s);

                break;
            }
        }

    }

    /**
     * 复制list的三种方法,源list的值发生改变不影响新的list;
     */
    @Test
    public void test01() {
        List<String> list1 = new ArrayList<>();
        list1.add("张三");
        list1.add("赵四");
        list1.add("五万");
        list1.add("你妹");

        System.out.println(list1);
        List<String> list2 = new ArrayList<>();
        //构造器
        List<String> list3 = new ArrayList<>(list1);
        //遍历赋值
        List<String> list4 = new ArrayList<>();
        for (String s : list1) {
            list4.add(s);
        }
        //addAll
        list2.addAll(list1);
        Collections.shuffle(list1);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
        System.out.println(list4);
    }

    @Test
    public void test02() {
        Person person = new Person();
        List<Book> books = new ArrayList<>();
        person.setBookList(books);
        System.out.println(person);
        books.add(new Book("水浒传", new BigDecimal(300)));
        System.out.println(person);
    }

    @Test
    public void test03() {

        List<Book> books = new ArrayList<>();
        Book book1 = new Book("水浒传", new BigDecimal(300));
        books.add(book1);
        System.out.println(books);
        List<Book> books1 = new ArrayList<>();
        books1.addAll(books);
        book1.setBookName("三国演义");
        System.out.println(books);
        System.out.println(books1);

    }

    @Test
    public void test04() {
        Book book1 = new Book("水浒传", new BigDecimal(300));
        System.out.println(book1);
    }


    @Test
    public void test05() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("qbc");
        list.add("4456");
        System.out.println(list);
        System.out.println(list);
    }

    @Test
    public void test06() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book("水浒传", new BigDecimal(300));
        Book book2 = new Book("三国演义", new BigDecimal(300));
        Book book3 = new Book("红楼梦", new BigDecimal(300));
        books.add(book1);
        books.add(book2);
        books.add(book3);
        Book book4 = new Book();
        Book book5 = new Book("三国演义", new BigDecimal(300));
        book4.setBookName("红楼梦");
        List<Book> bookList = new ArrayList<>();
        bookList.add(book4);
        bookList.add(book5);
        books.removeAll(bookList);

        System.out.println(books);

    }

    @Test
    public void test07() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book("水浒传", new BigDecimal(300));
        Book book2 = new Book("三国演义", new BigDecimal(300));
        Book book3 = new Book("红楼梦", new BigDecimal(300));

        books.add(book1);
        books.add(book2);
        books.add(book3);

        List<Book> bookList = new ArrayList<>();

        bookList.addAll(books);

        System.out.println(bookList);

    }


    @Test
    public void test09() {
        List<User> users = new ArrayList<>();
        User b1 = new User("水浒传", 10);
        User b2 = new User("三国演义", 20);
        User b3 = new User("红楼梦", 30);
        User b4 = new User();
        User b5 = new User();
        User b6 = new User();
        b4.setUserName("西游记");

        users.add(b1);
        users.add(b2);
        users.add(b3);
        users.add(b4);
        users.add(b5);
        users.add(b6);

        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getAge() == null && o2.getAge() == null) {
                    return 0;
                }
                if (o1.getAge() == null) {
                    return 1;
                }
                if (o2.getAge() == null) {
                    return -1;
                }
                return o1.getAge().compareTo(o2.getAge());
            }
        });

        System.out.println(JSON.toJSONString(users));
        System.out.println(JSON.toJSONString(users));

    }
}

package com.ca.fire.test.java8.stream;

import com.ca.fire.test.domain.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestStream {

    /**
     * 测试forEach
     */
    @Test
    public void testForEach() {
        List<Book> list = getBookList();
        list.forEach(book -> {
            System.out.println(book.getBookName() + "," + book.getPrice().toString());
        });
    }

    /**
     * 测试forEach
     */
    @Test
    public void testFilter() {
        List<Book> list = getBookList();
        list.stream().filter(book -> book.getPrice().compareTo(new BigDecimal("150")) == 1).forEach(book -> {
            book.setPrice(book.getPrice().subtract(new BigDecimal(10)));
        });
        list.forEach(book -> {
            System.out.println(book.getBookName() + "," + book.getPrice().toString());
        });
    }

    //DISTINCT
    //COLLECT
    @Test
    public void testCollect() {
        List<Book> list = getBookList();
        List<String> strings = list.stream().map(x -> x.getBookName()).collect(Collectors.toList());

        strings.stream().forEach(System.out::println);
    }

    private List<Book> getBookList() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setBookName("红楼梦");
        book1.setPrice(new BigDecimal("100.00"));
        Book book2 = new Book();
        book2.setId(2);
        book2.setBookName("西游记");
        book2.setPrice(new BigDecimal("120.00"));
        Book book3 = new Book();
        book3.setId(3);
        book3.setBookName("三国演义");
        book3.setPrice(new BigDecimal("150.00"));
        Book book4 = new Book();
        book4.setId(4);
        book4.setBookName("水浒传");
        book4.setPrice(new BigDecimal("200.00"));
        List<Book> list = new ArrayList<>();
        list.add(book1);
        list.add(book2);
        list.add(book3);
        list.add(book4);
        return list;
    }
}

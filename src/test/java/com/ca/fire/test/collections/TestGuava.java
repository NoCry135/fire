package com.ca.fire.test.collections;

import com.ca.fire.domain.bean.User;
import com.ca.fire.test.domain.Book;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/1/9
 */
public class TestGuava {

    public static void main(String[] args) {

        List<Book> books = new ArrayList<>();
        Book book1 = new Book("水浒传", new BigDecimal(20));
        Book book2 = new Book("三国演义", new BigDecimal(300));
        Book book3 = new Book("红楼梦", new BigDecimal(10));
        books.add(book1);
        books.add(book2);
        books.add(book3);
        //是否已拒收
        List<Book> list = Lists.newArrayList(IterableUtils.filteredIterable(books, new Predicate<Book>() {
            @Override
            public boolean evaluate(Book user) {
                return user.getPrice() != null && user.getPrice().compareTo(new BigDecimal(30)) < 0 ? false : true;
            }
        }));
        System.out.println(list);
    }
}

package com.ca.fire.test.annotation;

import com.ca.fire.aspect.UserParamValidatorAspect;
import com.ca.fire.test.domain.Book;
import org.junit.Test;

public class TestAnnontion {

    @Test
    public void test01() throws IllegalAccessException {
        Book book = new Book();
        UserParamValidatorAspect.annoProcess(book);
        System.out.println(book);
    }
}

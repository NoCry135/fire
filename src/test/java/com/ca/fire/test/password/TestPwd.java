package com.ca.fire.test.password;

import com.ca.fire.util.UserPdEncodeUtils;
import com.ca.fire.util.UserPwdEncodeUtils;
import org.junit.Test;

public class TestPwd {

    @Test
    public void test01() {
        String str = "admin";
        String encode = UserPdEncodeUtils.encode(str);
        System.out.println(encode);

        String encode1 = UserPwdEncodeUtils.encode(str);
        System.out.println(encode1);
        String str1 = "super";
        String pwd = UserPdEncodeUtils.encode(str1);
        String s = UserPwdEncodeUtils.decodeByCookie(pwd);
        System.out.println(s);
        System.out.println(UserPdEncodeUtils.decodeByCookie(pwd));

    }
}

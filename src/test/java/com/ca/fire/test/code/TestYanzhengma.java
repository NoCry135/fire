package com.ca.fire.test.code;

import com.ca.fire.domain.bean.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by caian on 2019/11/19
 */
public class TestYanzhengma {

    @Test
    public void test01() {
        int dividend = 10;
        int divisor = -8;
        System.out.println(divisor << 2);
        System.out.println(divisor >>> 2);
        System.out.println(divisor >> 2);
        //^ 异或
        System.out.println(dividend > 0 ^ divisor > 0);
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0, i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, (j - i));
            } else {
                set.add(s.charAt(i++));
            }
        }
        return ans;
    }

    public static void main(String[] args) throws InterruptedException {

        List<TaskBusinessType> taskBusinessTypeList = (List<TaskBusinessType>) CollectionUtils.select(new ArrayList<TaskBusinessType>(), new Predicate<TaskBusinessType>() {
            @Override
            public boolean evaluate(TaskBusinessType taskBusinessType) {
                if (1 == Integer.parseInt(taskBusinessType.getTaskTypeNo())
                ) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        // assa

        //FIXME
//        /对于暂时被注释掉，后续可能恢复使用的代码片断，在注释代码上方，统一规定使用三个斜杠(///)来说明注释掉代码的理由
        ArrayList<User> list = new ArrayList<>();
        System.out.println("start");
        int i = 0;
        while (true) {
            if (list.size() == 10) {
                list.clear();
            }
            User user = new User();
            user.setUserName("user" + i++);
            user.setAge(i++);
            list.add(user);
            TimeUnit.SECONDS.sleep(2);
            System.out.println(i++);
        }

    }
}

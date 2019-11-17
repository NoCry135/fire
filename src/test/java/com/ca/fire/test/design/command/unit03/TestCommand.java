package com.ca.fire.test.design.command.unit03;


import java.util.Iterator;
import java.util.List;

public class TestCommand {

    public static void main(String[] args) {
        List queue = producer.produceRequests();
        for (Iterator it = queue.iterator(); it.hasNext(); )
//取出List 中东东,其他特征都不能确定,只能保证一            个特征是100 % 正确,
// 他们至少是接口Command 的"儿子".所以强制转换                    类型为接口Command
            ((Command) it.next()).execute();
    }
}

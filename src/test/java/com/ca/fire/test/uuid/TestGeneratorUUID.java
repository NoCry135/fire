package com.ca.fire.test.uuid;

import com.alibaba.fastjson.JSON;

import com.ca.fire.until.uuid.MyUuidCommon;
import com.ca.fire.until.uuid.UuidGenerator;
import org.junit.Test;

public class TestGeneratorUUID {

    @Test
    public void testUUid(){
        UuidGenerator uuidGenerator = new UuidGenerator();
        MyUuidCommon phone = uuidGenerator.getMyUUid("Phone");
        System.out.println(JSON.toJSONString(phone));
    }
}

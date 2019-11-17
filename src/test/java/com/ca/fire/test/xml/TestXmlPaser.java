package com.ca.fire.test.xml;

import com.ca.fire.until.XMLUtil;

public class TestXmlPaser {

    public static void main(String[] args) {
        String dataXml = "";


        AsnAbandon userTest = (AsnAbandon) XMLUtil.convertXmlStrToObject(AsnAbandon.class, dataXml);
        System.out.println(userTest);
    }


}

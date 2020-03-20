package com.ca.fire.test.string;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by baizhihong on 2018/8/20.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BoxNoAndSerialNoList {

    @XmlElement(name = "bn")
    private String boxNo;

    @XmlElement(name = "sn")
    private String serialNo;

    private StringBuilder serialNoStr;


    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }


    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public StringBuilder getSerialNoStr() {
        return serialNoStr;
    }

    public void setSerialNoStr(StringBuilder serialNoStr) {
        this.serialNoStr = serialNoStr;
    }
}

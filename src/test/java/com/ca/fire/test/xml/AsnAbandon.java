package com.ca.fire.test.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "com.")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = {


        "poType",

        "pnNo",

        "asnNo",
        "source",
        "remark",
        "totalQty",
})
public class AsnAbandon {


    private String asnNo;


    private Integer poType;

    private String pnNo;

    //来源
    private Integer source;

    //状态
    private Integer inboundStatus;

    //数量
    private float totalQty;


    //备注
    private String remark;


    public String getAsnNo() {
        return asnNo;
    }

    public void setAsnNo(String asnNo) {
        this.asnNo = asnNo;
    }


    public Integer getPoType() {
        return poType;
    }

    public void setPoType(Integer poType) {
        this.poType = poType;
    }

    public String getPnNo() {
        return pnNo;
    }

    public void setPnNo(String pnNo) {
        this.pnNo = pnNo;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getInboundStatus() {
        return inboundStatus;
    }

    public void setInboundStatus(Integer inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    public float getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(float totalQty) {
        this.totalQty = totalQty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AsnAbandon{" +

                ", poType=" + poType +
                ", pnNo='" + pnNo + '\'' +
                ", source=" + source +
                ", inboundStatus=" + inboundStatus +
                ", totalQty=" + totalQty +
                ", remark='" + remark + '\'' +
                '}';
    }
}

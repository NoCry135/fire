package com.ca.fire.test.collections;

public class Goods {

    private String goodsNo;

    private String lotNo;

    private String name;

    private Integer qty;

    public Goods(String goodsNo, String lotNo, String name, Integer qty) {
        this.goodsNo = goodsNo;
        this.lotNo = lotNo;
        this.name = name;
        this.qty = qty;
    }

    public Goods() {
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}

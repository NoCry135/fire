package com.ca.fire.domain.bean;

import java.io.Serializable;
import java.util.Date;

public class Club implements Serializable {
    // 名称
    private String name;
    // 描述
    private String info;
    // 创建日期
    private Date createDate;

    // 排名
    private int rank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Club() {
    }

    public Club(String name, String info, Date createDate, int rank) {
        this.name = name;
        this.info = info;
        this.createDate = createDate;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", createDate=" + createDate +
                ", rank=" + rank +
                '}';
    }
}

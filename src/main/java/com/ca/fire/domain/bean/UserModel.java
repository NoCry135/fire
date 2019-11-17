package com.ca.fire.domain.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;
import java.util.Date;

public class UserModel extends BaseRowModel implements Serializable {

    @ExcelProperty(index = 0, value = "姓名")
    private String userName;

    @ExcelProperty(index = 1, value = "密码")
    private String passWord;

    @ExcelProperty(index = 2, value = "邮箱")
    private String email;

    @ExcelProperty(index = 4, value = "手机")
    private String telPhone;

    @ExcelProperty(index = 5, value = "id")
    private Long id;

    @ExcelProperty(index = 6, value = "创建人")
    private String createUser;

    @ExcelProperty(index = 7, value = "修改人")
    private String updateUser;

    @ExcelProperty(index = 8, value = "创建时间", format = "yyyy-MM-dd")
    private Date createTime;

    @ExcelProperty(index = 9, value = "修改时间", format = "yyyy-MM-dd")
    private Date updateTime;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}

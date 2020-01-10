package com.ca.fire.test.domain;

public class JuniorStudent extends Student {

    private int grade;

    public JuniorStudent(String str, int grade) {
        super(str);
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

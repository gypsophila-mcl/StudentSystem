package com.mcl.entity;

import java.util.Date;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
public class Student {
    private int id;
    private String xh;
    private String name;
    private String gender;
    private Date birthday;
    private String idnum;
    private String tel;
    private int classno;
    private String className;

    public Student() {
    }

    public Student(int id, String xh, String name, String gender, Date birthday, String idnum, String tel, int classno) {
        this.id = id;
        this.xh = xh;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.idnum = idnum;
        this.tel = tel;
        this.classno = classno;
    }

    public Student(String xh, String name, String gender, Date birthday, String idnum, String tel, int classno) {
        this.xh = xh;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.idnum = idnum;
        this.tel = tel;
        this.classno = classno;
    }

    public Student(int id, String xh, String name, String gender, Date birthday, String idnum, String tel, String className) {
        this.id = id;
        this.xh = xh;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.idnum = idnum;
        this.tel = tel;
        this.className = className;
    }
    public Student(int id, String xh, String name, String gender, Date birthday, String idnum, String tel) {
        this.id = id;
        this.xh = xh;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.idnum = idnum;
        this.tel = tel;
    }

    public Student(String xh, String name, String gender, Date birthday, String idnum, String tel, String className) {
        this.xh = xh;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.idnum = idnum;
        this.tel = tel;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassno() {
        return classno;
    }

    public void setClassno(int classno) {
        this.classno = classno;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", xh='" + xh + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", idnum='" + idnum + '\'' +
                ", tel='" + tel + '\'' +
                ", classno=" + classno +
                ", className='" + className + '\'' +
                '}';
    }

}

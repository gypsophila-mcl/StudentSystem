package com.mcl.entity;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: 教师实体类
 */
public class Teacher {
    private int id;
    private String teacherNo;//教师编号
    private String teacherName;//教师姓名
    private int age;//教师年龄
    private String gender;//教师性别
    private String tel;//教师电话

    public Teacher() {
    }

    public Teacher(String teacherName, int age, String gender, String tel) {
        this.teacherName = teacherName;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
    }

    public Teacher(int id, String teacherName, int age, String gender,String tel) {
        this.id = id;
        this.teacherName = teacherName;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
    }

    public Teacher(String teacherNo, String teacherName, int age, String gender, String tel) {
        this.teacherNo = teacherNo;
        this.teacherName = teacherName;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherNo='" + teacherNo + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}

package com.mcl.entity;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: 班级实体类
 */
public class Classes {
    private int id;
    private String className;
    private int stdNum;//学生人数
    private String manager;//班主任
    private String teacherNo;//教师编号

    public Classes() {
    }

    public Classes(String className, int stdNum, String manager, String teacherNo) {
        this.className = className;
        this.stdNum = stdNum;
        this.manager = manager;
        this.teacherNo = teacherNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStdNum() {
        return stdNum;
    }

    public void setStdNum(int stdNum) {
        this.stdNum = stdNum;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", stdNum=" + stdNum +
                ", manager='" + manager + '\'' +
                ", teacherNo='" + teacherNo + '\'' +
                '}';
    }
}

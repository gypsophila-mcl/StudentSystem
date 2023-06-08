package com.mcl.dao;

import com.mcl.entity.Student;

import java.util.List;

public interface StudentDao {

    //查询所有学生信息
    List<Student> findAll();

    //分页查询
    List<Student> getPage(int currentPage, int pageSize);

    //查询总记录数
    int getTotal();

    //添加学生方法
    boolean addStudent(Student student);

    //根据学号查询学生信息
    Student findStdByXh(String xh);

    //根据 id 查询学生信息
    Student findStdById(int id);

    //修改学生信息
    boolean updateStudent(Student student);

    //根据 Id 删除学生
    boolean delStudentById(int id);

    //批量删除
    boolean batchDelStd(int[] allId);

    //模糊查询学生信息
    List<Student> fuzzyQueryStd(String name);
}

package com.mcl.service;

import com.mcl.entity.PageInfo;
import com.mcl.entity.Student;

import java.util.List;

public interface StudentService {
    //查询所有学生数据
    List<Student> findAll();

    /**
     * @param currentPage: 当前页
     * @param pageSize: 页面数据数
     * @return PageInfo
     * @description 分页查询
     */
    PageInfo<Student> getPage(int currentPage,int pageSize);

    //添加学生方法
    boolean addStudent(Student student);

    //根据 id 查询学生信息
    Student findStdById(int id);

    //修改学生信息
    boolean updateStudent(Student student);

    //根据 id 删除学生
    boolean delStudentById(int id);

    //批量删除
    boolean batchDelStd(int[] allId);

    //模糊查询
    List<Student> searchStudent(String name);
}

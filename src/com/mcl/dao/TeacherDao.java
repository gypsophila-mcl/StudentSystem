package com.mcl.dao;

import com.mcl.entity.Classes;
import com.mcl.entity.Student;
import com.mcl.entity.Teacher;

import java.util.List;

public interface TeacherDao {
    //查找所有教师
    List<Teacher> findAll();

    //根据teacherNo查找老师
    Teacher findByTeacherNo(String teacherNo);

    //查找分页教师信息
    List<Teacher> getPageList(int currentPage, int pageSize);

    //查询总数
    int getTotal();

    //添加教师
    boolean addTeacher(Teacher teacher);

    //修改教师信息
    boolean updateTeacher(Teacher teacher);

    //根据id查询教师信息
    Teacher findById(int id);

    //根据 Id 删除教师信息
    boolean delTeacherById(int id);

    //批量删除
    boolean batchDelTeacher(int[] allId);

    //模糊查询学生信息
    List<Teacher> fuzzyQueryTea(String name);

    //查询老师是否任教
    List<Classes> findTeach(String teacherno);

}

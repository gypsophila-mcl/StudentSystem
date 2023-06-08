package com.mcl.dao;

import com.mcl.entity.Classes;
import com.mcl.entity.Student;

import java.util.List;

public interface ClassesDao {
    //查询所有班级信息
    List<Classes> findAll();

    //分页查询
    List<Classes> getPage(int currentPage, int pageSize);

    //查询总记录数
    int getTotal();

    //根据 id 查询学生对应班级人数
    int countByClassno(int id);

    //添加班级方法
    boolean addClass(Classes classes);

    //根据 id 查询班级信息
    Classes findClsById(int id);

    //修改班级信息
    boolean updateClass(Classes classes);

    //根据 Id 删除班级信息
    boolean delClassById(int id);

    //批量删除
    boolean batchDelCls(int[] allId);

    //模糊查询学生信息
    List<Classes> fuzzyQueryCls(String name);

    //根据班级名字查询班级信息
    Classes findByClsName(String name);
}

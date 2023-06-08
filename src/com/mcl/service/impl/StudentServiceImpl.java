package com.mcl.service.impl;

import com.mcl.dao.StudentDao;
import com.mcl.dao.impl.StudentDaoImpl;
import com.mcl.entity.PageInfo;
import com.mcl.entity.Student;
import com.mcl.exception.MyException;
import com.mcl.service.StudentService;

import java.util.List;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: 与学生操作相关的类
 */
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    //获取页面信息
    @Override
    public PageInfo<Student> getPage(int currentPage, int pageSize) {
        int total = studentDao.getTotal();
        List<Student> studentList = studentDao.getPage(currentPage, pageSize);
        int maxNum = (int)Math.ceil(total / (double)pageSize);
        int[] navigatePageNum = new int[maxNum];
        for (int i = 0; i < maxNum; i++) {
            navigatePageNum[i] = i+1;
        }
        return (PageInfo<Student>) new PageInfo(currentPage, pageSize, total, studentList, navigatePageNum, maxNum);
    }

    //添加学生
    @Override
    public boolean addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    //根据学生ID查询学生信息
    @Override
    public Student findStdById(int id) {
        return studentDao.findStdById(id);
    }

    //更新学生信息
    @Override
    public boolean updateStudent(Student student) {
        Student stdByXh = studentDao.findStdByXh(student.getXh());
        if (stdByXh==null || stdByXh.equals(student.getXh())){
            return studentDao.updateStudent(student);
        }
        throw new MyException("学号重复，修改失败");
    }

    //根据id删除学生信息
    @Override
    public boolean delStudentById(int id) {
        return studentDao.delStudentById(id);
    }

    //批量删除
    @Override
    public boolean batchDelStd(int[] allId) {
        return studentDao.batchDelStd(allId);
    }

    @Override
    public List<Student> searchStudent(String name) {
        return studentDao.fuzzyQueryStd(name);
    }
}

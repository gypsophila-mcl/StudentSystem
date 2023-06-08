package com.mcl.service.impl;

import com.mcl.dao.TeacherDao;
import com.mcl.dao.impl.TeacherDaoImpl;
import com.mcl.entity.Classes;
import com.mcl.entity.PageInfo;
import com.mcl.entity.Student;
import com.mcl.entity.Teacher;
import com.mcl.exception.MyException;
import com.mcl.service.TeacherService;

import java.util.List;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
public class TeacherServiceImpl implements TeacherService {
    private TeacherDao teacherDao = new TeacherDaoImpl();
    @Override
    public List<Teacher> findAll() {
        List<Teacher> all = teacherDao.findAll();
        return all;
    }

    @Override
    public Teacher findByTeacherNo(String teacherNo) {
        Teacher teacher = teacherDao.findByTeacherNo(teacherNo);
        return teacher;
    }

    @Override
    public PageInfo<Teacher> getPage(int currentPage, int pageSize) {
        List<Teacher> list = teacherDao.getPageList(currentPage, pageSize);
        int total = teacherDao.getTotal();
        int maxNum = (int)Math.ceil(total / (double)pageSize);
        int[] navigatePageNum = new int[maxNum];
        for (int i = 0; i < navigatePageNum.length; i++) {
            navigatePageNum[i] = i + 1;
        }
        return new PageInfo<>(currentPage, pageSize, total, list, navigatePageNum, maxNum);
    }
    //添加教师
    @Override
    public boolean addTeacher(Teacher teacher) {
        String teacherNo = teacher.getTeacherNo();
        Teacher byTeacherNo = teacherDao.findByTeacherNo(teacherNo);
        if (byTeacherNo!=null){
            throw new MyException("教师编号重复，添加失败");
        }
        return teacherDao.addTeacher(teacher);
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        String teacherNo = teacher.getTeacherNo();
        Teacher byTeacherNo = teacherDao.findByTeacherNo(teacherNo);
        if (byTeacherNo==null || byTeacherNo.getTeacherNo().equals(teacherNo)){
            return teacherDao.updateTeacher(teacher);
        }
        throw new MyException("教师编号重复，修改失败");
    }

    @Override
    public Teacher findById(int id) {
        return teacherDao.findById(id);
    }

    @Override
    public boolean delTeacherById(int id) {
        Teacher teacher = teacherDao.findById(id);
        List<Classes> teach = teacherDao.findTeach(teacher.getTeacherNo());
        if (teach!=null){
            throw new MyException("此教师仍在任教，无法删除");
        }
        return teacherDao.delTeacherById(id);
    }

    @Override
    public boolean batchDelTeacher(int[] allId) {
        return teacherDao.batchDelTeacher(allId);
    }

    @Override
    public List<Teacher> searchTeacher(String name) {
        return null;
    }
}

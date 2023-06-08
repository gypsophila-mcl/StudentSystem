package com.mcl.service.impl;

import com.mcl.dao.ClassesDao;
import com.mcl.dao.impl.ClassesDaoImpl;
import com.mcl.dao.impl.TeacherDaoImpl;
import com.mcl.entity.Classes;
import com.mcl.entity.PageInfo;
import com.mcl.entity.Teacher;
import com.mcl.exception.MyException;
import com.mcl.service.ClassesService;
import com.mcl.service.TeacherService;
import com.mcl.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
public class ClassesServiceImpl implements ClassesService {
    private ClassesDao classesDao = new ClassesDaoImpl();
    private TeacherService teacherService = new TeacherServiceImpl();

    //查找所有班级信息
    @Override
    public List<Classes> findAll() {
        return classesDao.findAll();
    }

    //获取分页数据
    @Override
    public PageInfo<Classes> getPage(int currentPage, int pageSize) {
        List<Classes> list = classesDao.getPage(currentPage, pageSize);
        int total = classesDao.getTotal();
        int maxNum = (int)Math.ceil(total / (double)pageSize);
        int[] navigatePageNum = new int[maxNum];
        for (int i = 0; i < maxNum; i++) {
            navigatePageNum[i] = i+1;
        }
        return new PageInfo<>(currentPage,pageSize,total,list,navigatePageNum,maxNum);
    }

    //添加班级
    @Override
    public boolean addClass(Classes classes) {
        Teacher teacher = teacherService.findByTeacherNo(classes.getTeacherNo());
        classes.setManager(teacher.getTeacherName());
        Classes byClsName = classesDao.findByClsName(classes.getClassName());
        if (byClsName!=null){
            throw new MyException("班级姓名重复，添加失败");
        }
        return classesDao.addClass(classes);
    }

    @Override
    public Classes findClsById(int id) {
        return classesDao.findClsById(id);
    }

    @Override
    public boolean updateClass(Classes classes) {
        //传入的classes对象缺少manager属性
        Teacher teacher = teacherService.findByTeacherNo(classes.getTeacherNo());
        //设置manager属性
        classes.setManager(teacher.getTeacherName());
//        //测试代码
//        System.out.println(classes);

        Classes byClsName = classesDao.findByClsName(classes.getClassName());
        if (byClsName==null || byClsName.getClassName().equals(classes.getClassName())){
            return classesDao.updateClass(classes);
        }
        throw new MyException("班级名称重复，修改失败");
    }

    @Override
    public boolean delClassById(int id) {
        Classes clsById = classesDao.findClsById(id);
        if (clsById.getStdNum()!=0){
            throw new MyException("班级中还有学生呢，无法删除");
        }else {
            return classesDao.delClassById(id);
        }
    }

    @Override
    public boolean batchDelCls(int[] allId) {
        return classesDao.batchDelCls(allId);
    }

    @Override
    public List<Classes> searchClasses(String name) {
        return null;
    }


}

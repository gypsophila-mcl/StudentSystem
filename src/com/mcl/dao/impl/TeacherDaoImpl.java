package com.mcl.dao.impl;

import com.mcl.dao.TeacherDao;
import com.mcl.entity.Classes;
import com.mcl.entity.PageInfo;
import com.mcl.entity.Teacher;
import com.mcl.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
public class TeacherDaoImpl implements TeacherDao {

    //查找所有教师
    @Override
    public List<Teacher> findAll() {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_teacher";
        List<Teacher> query = null;
        try {
            query = queryRunner.query(sql, new BeanListHandler<>(Teacher.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;

    }

    //根据teacherNo查找老师
    @Override
    public Teacher findByTeacherNo(String teacherNo) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_teacher where teacherNo=?";
        Teacher teacher = null;
        try {
            teacher = queryRunner.query(sql, new BeanHandler<>(Teacher.class),teacherNo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return teacher;
    }

    //查找分页教师信息
    @Override
    public List<Teacher> getPageList(int currentPage, int pageSize) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_teacher limit ?,?";
        List<Teacher> query = null;
        try {
            query = queryRunner.query(sql, new BeanListHandler<>(Teacher.class), (currentPage-1)*pageSize,pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;
    }

    //查询总数
    @Override
    public int getTotal() {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select count(*) from tb_teacher";
        int total = 0;
        try {
            total = Integer.parseInt(queryRunner.query(sql, new ScalarHandler<>()).toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return total;
    }

    //添加教师
    @Override
    public boolean addTeacher(Teacher teacher) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "insert into tb_teacher (teacherNo,teacherName,age,gender,tel) values(?,?,?,?,?)";
        int update = 0;
        try {
            update = queryRunner.update(sql, teacher.getTeacherNo(), teacher.getTeacherName(), teacher.getAge(), teacher.getGender(), teacher.getTel());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update>0;
    }

    //修改教师信息
    @Override
    public boolean updateTeacher(Teacher teacher) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "update tb_teacher set teacherNo=?,teacherName=?,age=?,gender=?,tel=? where id=?";
        int update = 0;
        try {
            update = queryRunner.update(sql, teacher.getTeacherNo(), teacher.getTeacherName(), teacher.getAge(), teacher.getGender(), teacher.getTel(), teacher.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update>0;
    }

    //根据id查询教师信息
    @Override
    public Teacher findById(int id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_teacher where id=?";
        Teacher query = null;
        try {
            query = queryRunner.query(sql, new BeanHandler<>(Teacher.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;
    }

    @Override
    public boolean delTeacherById(int id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "delete from tb_teacher where id=?";
        int del = 0;
        try {
            del = queryRunner.update(sql, id);
            sql = "alter table tb_teacher AUTO_INCREMENT = 1";
            queryRunner.update(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return del>0;
    }

    @Override
    public boolean batchDelTeacher(int[] allId) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "delete from tb_teacher where id in (";
        int length = allId.length;
        for (int i = 0; i < length; i++) {
            if (i != length-1){
                sql += allId[i] + ",";
            }else {
                sql += allId[i] + ")";
            }
        }
        int update = 0;
        try {
            update = queryRunner.update(sql);
            sql = "alter table tb_teacher AUTO_INCREMENT = 1";
            queryRunner.update(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update>0;
    }

    @Override
    public List<Teacher> fuzzyQueryTea(String name) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_teacher where teacherName like '%" + name + "%'";
        List<Teacher> query = null;
        try {
            query = queryRunner.query(sql, new BeanListHandler<>(Teacher.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;
    }

    //查询老师是否任教
    @Override
    public List<Classes> findTeach(String teacherno) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_class where teacherNo=?";
        List<Classes> query = null;
        try {
            query = queryRunner.query(sql, new BeanListHandler<>(Classes.class), teacherno);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;
    }
}

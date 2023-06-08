package com.mcl.dao.impl;

import com.mcl.dao.ClassesDao;
import com.mcl.entity.Classes;
import com.mcl.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
public class ClassesDaoImpl implements ClassesDao {
    //查询所有班级信息
    @Override
    public List<Classes> findAll() {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_class";
        List<Classes> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<>(Classes.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    //分页查询
    @Override
    public List<Classes> getPage(int currentPage, int pageSize) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_class limit ?,?";
        List<Classes> classesList = null;
        try {
            classesList = queryRunner.query(sql, new BeanListHandler<>(Classes.class),(currentPage-1)*pageSize,pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return classesList;
    }


    //查询班级总数
    @Override
    public int getTotal() {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select count(*) from tb_class";
        int total = 0;
        try {
            total = Integer.parseInt(queryRunner.query(sql, new ScalarHandler<>()).toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return total;
    }

    //根据 id 查询学生对应班级人数
    @Override
    public int countByClassno(int id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select stdNum from tb_class where id=(select classno from tb_student where id=?)";
        int stdNum = 0;
        try {
            stdNum = Integer.parseInt(queryRunner.query(sql, new ScalarHandler<>(), id).toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return stdNum;
    }

    //添加班级信息
    @Override
    public boolean addClass(Classes classes) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "insert into tb_class (className,stdNum,manager,teacherNo) values(?,?,?,?)";
        int update = 0;
        try {
            update = queryRunner.update(sql, classes.getClassName(), classes.getStdNum(), classes.getManager(), classes.getTeacherNo());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update>0;
    }

    @Override
    public Classes findClsById(int id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_class where id=?";
        Classes query = null;
        try {
            query = queryRunner.query(sql, new BeanHandler<>(Classes.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;
    }

    @Override
    public boolean updateClass(Classes classes) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "update tb_class set className=?, stdNum=?, manager=?, teacherNo=? where id=?";
        int update = 0;
        try {
            update = queryRunner.update(sql, classes.getClassName(), classes.getStdNum(), classes.getManager(), classes.getTeacherNo(), classes.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update>0;
    }

    @Override
    public boolean delClassById(int id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "delete from tb_class where id=?";
        int del = 0;
        try {
            del = queryRunner.update(sql, id);
            sql = "alter table tb_class AUTO_INCREMENT = 1";
            queryRunner.update(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return del>0;
    }

    @Override
    public boolean batchDelCls(int[] allId) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "delete from tb_class where id in (";
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
            sql = "alter table tb_class AUTO_INCREMENT = 1";
            queryRunner.update(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update>0;
    }

    @Override
    public List<Classes> fuzzyQueryCls(String name) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_class where classname like '%" + name + "%'";
        List<Classes> query = null;
        try {
            query = queryRunner.query(sql, new BeanListHandler<>(Classes.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;
    }

    @Override
    public Classes findByClsName(String name) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_class where classname=?";
        Classes query = null;
        try {
            query = queryRunner.query(sql, new BeanHandler<>(Classes.class), name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;
    }
}

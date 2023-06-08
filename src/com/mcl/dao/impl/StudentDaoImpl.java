package com.mcl.dao.impl;

import com.mcl.dao.StudentDao;
import com.mcl.entity.Student;
import com.mcl.exception.MyException;
import com.mcl.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> findAll() {
        // 使用 QueryRunner
//        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
//        String sql = "select * from tb_student";
//        List<Student> studentList = null;
//        try {
//            studentList = queryRunner.query(sql, new BeanListHandler<>(Student.class));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        // 正常使用jdbc
        Connection connection = DruidUtils.getConnection();
        String sql = "select a.*,b.className from tb_student a,tb_class b where a.classno=b.id order by xh asc";
        ArrayList<Student> studentList = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            studentList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String xh = rs.getString("xh");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                Date birthday = rs.getDate("birthday");
                String idnum = rs.getString("idnum");
                String tel = rs.getString("tel");
                int classno = rs.getInt("classno");
                String className = rs.getString("classname");
                studentList.add(new Student(id, xh, name, gender, birthday, idnum, tel, className));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studentList;
    }

    @Override
    public List<Student> getPage(int currentPage, int pageSize) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select a.*,b.className " +
                "from tb_student a,tb_class b " +
                "where a.classno=b.id order by a.xh asc limit ?, ?";
        List<Student> students = null;
        try {
            students = queryRunner.query(sql, new BeanListHandler<>(Student.class), (currentPage - 1) * pageSize, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }

    //查询总记录数
    @Override
    public int getTotal() {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select count(*) from tb_student";
        int count = 0;
        try {
            count = Integer.parseInt(queryRunner.query(sql, new ScalarHandler<>()).toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    //添加学生
    @Override
    public boolean addStudent(Student student) throws MyException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "insert into tb_student (xh,name,gender,birthday,idnum,tel,classno) values(?,?,?,?,?,?,?)";

        Student stdById = findStdByXh(student.getXh());
        if (stdById != null) {
            throw new MyException("学号重复，无法添加");
        } else {
            int update = 0;
            try {
                update = queryRunner.update(sql, student.getXh(), student.getName(), student.getGender(), student.getBirthday(), student.getIdnum(), student.getTel(), student.getClassno());

                sql = "select stdNum from tb_class where id=?";
                int classno = student.getClassno();
                int stdNum = queryRunner.query(sql, new ScalarHandler<>(), classno);
                stdNum++;

                sql = "update tb_class set stdNum=? where id=?";
                int update1 = queryRunner.update(sql, stdNum, classno);

                update = (update1 == update) ? update : 0;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return update > 0 ? true : false;
        }
    }

    //根据学号查询学生信息
    @Override
    public Student findStdByXh(String xh) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_student where xh=?";
        Student student = null;
        try {
            student = queryRunner.query(sql, new BeanHandler<>(Student.class), xh);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return student;
    }

    //根据ID查询学生
    @Override
    public Student findStdById(int id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_student where id=?";
        Student student = null;
        try {
            student = queryRunner.query(sql, new BeanHandler<>(Student.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return student;
    }

    @Override
    public boolean updateStudent(Student student) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "update tb_student set xh=?,name=?,gender=?,birthday=?,idnum=?,tel=?,classno=? where id=?";
        int update = 0;
        try {
            update = queryRunner.update(sql, student.getXh(), student.getName(), student.getGender(), student.getBirthday(), student.getIdnum(), student.getTel(), student.getClassno(), student.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update > 0 ? true : false;
    }

    @Override
    public boolean delStudentById(int id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "";
        Connection connection = DruidUtils.getConnection();
        int del = 0;
        try {
            connection.setAutoCommit(false);

            ClassesDaoImpl classDao = new ClassesDaoImpl();
            int stdNum = classDao.countByClassno(id);
            stdNum--;

            sql = "update tb_class set stdNum=? where id=(select classno from tb_student where id=?)";
            int update = queryRunner.update(sql, stdNum, id);
//            System.out.println(sql1);
//            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
//            preparedStatement.setInt(1,stdNum);
//            preparedStatement.setInt(2,id);
//            int update = preparedStatement.executeUpdate();

            sql = "delete from tb_student where id=?";
            del = queryRunner.update(sql, id);
//            System.out.println(sql);
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1,id);
//            del = preparedStatement.executeUpdate();

//            connection.commit();

//            Statement statement = connection.createStatement();
            //解决主键跳跃增长问题
            sql = "SET @auto_id = 0;";
            queryRunner.update(sql);
//            System.out.println(sql);
//            statement.execute(sql);

            sql = "UPDATE tb_student SET id = (@auto_id := @auto_id + 1);";
            queryRunner.update(sql);
//            System.out.println(sql);
//            statement.execute(sql);

            sql = "ALTER TABLE tb_student AUTO_INCREMENT = 1;";
            queryRunner.update(sql);
//            System.out.println(sql);
//            statement.execute(sql);
//            statement.close();

            del = (del == update) ? del : 0;
//            preparedStatement.close();

//            Statement statement = connection.createStatement();
            //解决主键跳跃增长问题
            sql = "SET @auto_id = 0;";
            queryRunner.update(sql);
//            System.out.println(sql);
//            statement.execute(sql);

            sql = "UPDATE tb_student SET id = (@auto_id := @auto_id + 1);";
            queryRunner.update(sql);
//            System.out.println(sql);
//            statement.execute(sql);

            sql = "ALTER TABLE tb_student AUTO_INCREMENT = 1;";
            queryRunner.update(sql);
//            System.out.println(sql);
//            statement.execute(sql);
//            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return del > 0;
    }

    //批量删除
    @Override
    public boolean batchDelStd(int[] allId) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "delete from tb_student where id in (";
        int length = allId.length;
        for (int i = 0; i < length; i++) {
            if (i != length - 1) {
                sql += allId[i] + ",";
            } else {
                sql += allId[i] + ")";
            }
        }
        int update = 0;
        try {
            update = queryRunner.update(sql);
            sql = "alter table tb_student AUTO_INCREMENT = 1";
            queryRunner.update(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update > 0;
    }

    @Override
    public List<Student> fuzzyQueryStd(String name) {

        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select a.*,b.classname from tb_student a,tb_class b where a.classno=b.id and a.name like '%" + name + "%'";

        List<Student> query = null;
        try {
            query = queryRunner.query(sql, new BeanListHandler<>(Student.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return query;
    }
}

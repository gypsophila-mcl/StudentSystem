package com.mcl.test;

import com.mcl.dao.ClassesDao;
import com.mcl.dao.StudentDao;
import com.mcl.dao.TeacherDao;
import com.mcl.dao.impl.ClassesDaoImpl;
import com.mcl.dao.impl.StudentDaoImpl;
import com.mcl.dao.impl.TeacherDaoImpl;
import com.mcl.entity.*;
import com.mcl.service.ClassesService;
import com.mcl.service.TeacherService;
import com.mcl.service.impl.ClassesServiceImpl;
import com.mcl.service.impl.StudentServiceImpl;
import com.mcl.service.impl.TeacherServiceImpl;
import com.mcl.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
public class MyTest {
    ClassesDao classesDao = new ClassesDaoImpl();
    ClassesService classesService = new ClassesServiceImpl();
    StudentDao studentDao = new StudentDaoImpl();
    StudentServiceImpl studentService = new StudentServiceImpl();
    TeacherDao teacherDao = new TeacherDaoImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    /**
     * @param :
     * @return void
     * @description 测试添加用户 xyz/xyz123
     */
    @Test
    public void test1() throws SQLException {
        //获取数据库连接
        Connection connection = DruidUtils.getConnection();
        String sql = "insert into tb_user (username,password) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"xyz");
        preparedStatement.setString(2,"xyz123");
        preparedStatement.executeUpdate();
        DruidUtils.release(connection,preparedStatement,null);
    }

    /**
     * @param :
     * @return void
     * @description 测试查询所有用户
     */
//    @Test
//    public void test2() throws SQLException {
//        Connection connection = DruidUtils.getConnection();
//        Statement statement = connection.createStatement();
//        String sql = "select * from tb_user";
//        ResultSet resultSet = statement.executeQuery(sql);
//        ArrayList<User> users = new ArrayList<>();
//        while (resultSet.next()){
//            int id = resultSet.getInt("id");
//            String username = resultSet.getString("username");
//            String password = resultSet.getString("password");
//            users.add(new User(id,username,password));
//        }
//        DruidUtils.release(connection,statement,resultSet);
//        for (User user : users) {
//            System.out.println(user);
//        }
//    }

    /**
     * @param :
     * @return void
     * @description 便捷的进行查询(使用jar包)
     */
    @Test
    public void test3() throws SQLException {
        //添加数据
        //(1)创建 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        // 写要执行的 sql
        String sql = "insert into tb_user(username,password) values(?,?)";
        queryRunner.update(sql,"qwe","qwe123");
    }

    /**
     * @param :
     * @return void
     * @description 使用工具包简化jdbc查询操作
     */
    @Test
    public void test4() throws SQLException {
        // ResultSetHandler
        //常用的接口实现类：
        //      BeanHandler:查询结果只有一个（自动把查询结果封装成一个对象）
        //      BeanListHandler: 如果查询结果是多个，自动把查询结果封装成List集合
        //      ScalarHandler: 查询结果简单数字

        //查询一个（根据id查询数据）
        //（1）创建 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
//        //(2)写 sql
//        String sql = "select * from tb_user where id=?";
//        //(3)执行 sql
//        // query(sql,ResultHandler,param)
//        User user = queryRunner.query(sql, new BeanHandler<>(User.class), 1);
//        System.out.println(user);

//        //查询所有
//        //(2)写 sql
//        String sql = "select * from tb_user";
//        //(3)执行 sql
//        List<User> users = queryRunner.query(sql, new BeanListHandler<>(User.class));
//        for (User user : users) {
//            System.out.println(user);
//        }

        //查询简单数据
        //(2)写 sql
        String sql = "select count(*) from tb_user";
        //(3)执行 sql
        int count = Integer.valueOf(queryRunner.query(sql, new ScalarHandler<>()).toString());
        System.out.println(count);
    }

    @Test
    public void test5(){
        StudentServiceImpl studentService = new StudentServiceImpl();
        PageInfo page = studentService.getPage(1, 10);
        PageInfo page2 = studentService.getPage(2, 10);
        System.out.println(page2);
    }

    @Test
    public void test6(){
        StudentDao studentDao = new StudentDaoImpl();
        int[] allId = {1,2,3};
        boolean b = studentDao.batchDelStd(allId);
        System.out.println(b);
    }

    //测试查询所有学生（带班级名字）
//    @Test
//    public void test7(){
////        StudentDaoImpl studentDao = new StudentDaoImpl();
////        List<Student> all = studentDao.findAll();
////        for (Student student : all) {
////            System.out.println(student);
////        }
//
//        StudentServiceImpl studentService = new StudentServiceImpl();
//        PageInfo<Student> page = studentService.getPage(1, 50);
//        for (Student student : page.getList()) {
//            System.out.println(student);
//        }
//    }

    //测试查询所有班级
//    @Test
//    public void test8(){
//        ClassesServiceImpl classesService = new ClassesServiceImpl();
//        List<Classes> all = classesService.findAll();
//        for (Classes classes : all) {
//            System.out.println(classes);
//        }
//    }

    //测试班级分页查询
    @Test
    public void test9(){
        PageInfo<Classes> page = classesService.getPage(1, 10);
        for (Classes classes : page.getList()) {
            System.out.println(classes);
        }
    }

    //测试添加学生(班级信息改动)
    @Test
    public void test10(){
        Date date = new Date();
//        Student student = new Student("997", "test", "男", date, "111124555454569598", "14478547854", 10);
//        boolean b = studentService.addStudent(student);
        boolean b = studentService.delStudentById(17);
        System.out.println(b);


//        ClassDaoImpl classDao = new ClassDaoImpl();
//        int stdNum = classDao.countByClassno(18);
//        System.out.println(stdNum);
    }

    //测试查询班级页面信息
    @Test
    public void test11(){
        PageInfo<Classes> page = classesService.getPage(1, 10);
        for (Classes classes : page.getList()) {
            System.out.println(classes);
        }
    }

    //测试查询所有教师信息
//    @Test
//    public void test12(){
////        List<Teacher> all = teacherDao.findAll();
//        List<Teacher> all = teacherService.findAll();
//        for (Teacher teacher : all) {
//            System.out.println(teacher);
//        }
//    }

    //测试查询教师分页信息
    @Test
    public void test13(){
        PageInfo<Teacher> page = teacherService.getPage(1, 8);
        for (Teacher teacher : page.getList()) {
            System.out.println(teacher);
        }
//        List<Teacher> pageList = teacherDao.getPageList(1, 8);
//        for (Teacher teacher : pageList) {
//            System.out.println(teacher);
//        }
    }

//    //测试添加教师功能
//    @Test
//    public void test14(){
//        Teacher teacher = new Teacher("10666", "test2", 50, "男", "10548547785");
//        boolean b = teacherService.addTeacher(teacher);
//        System.out.println(b);
//    }

    //测试删除教师功能
    @Test
    public void test20(){
        boolean b = teacherService.delTeacherById(10);
        System.out.println(b);
    }

    //测试根据id查询班级信息
    @Test
    public void test15(){
        Classes classes = classesService.findClsById(2);
        Classes clsById = classesDao.findClsById(2);
        System.out.println(classes);
    }

    //测试修改教师
    @Test
    public void test16(){
        Teacher teacher = new Teacher("10666", "test3", 35, "女", "10548547785");
        teacher.setId(10);
        boolean b = teacherService.updateTeacher(teacher);
        System.out.println(b);
    }

    //测试按照id查找教师信息
    @Test
    public void test17(){
        Teacher byId = teacherService.findById(8);
        System.out.println(byId);
    }

    //测试删除班级功能
    @Test
    public void test18(){
        boolean b = classesService.delClassById(14);
        System.out.println(b);
    }

    //添加班级
    @Test
    public void test19(){
//        Classes classes5 = new Classes("test3", 0, "test1", "99666");
        Classes classes1 = new Classes("test4", 0, "test1", "99666");
        Classes classes2 = new Classes("test5", 0, "test1", "99666");
        Classes classes3 = new Classes("test6", 0, "test1", "99666");
        Classes classes4 = new Classes("test7", 0, "test1", "99666");
//        boolean b5 = classesService.addClass(classes5);
        boolean b1 = classesService.addClass(classes1);
        boolean b2 = classesService.addClass(classes2);
        boolean b3 = classesService.addClass(classes3);
        boolean b4 = classesService.addClass(classes4);
        System.out.println(b1+"\n"+b2+"\n"+b3+"\n"+b4);
    }

    //测试批量删除
    @Test
    public void test21(){
        int[] allId = {14,18,16,17};
        boolean b = classesService.batchDelCls(allId);
        System.out.println(b);
    }

    //测试添加教师功能
    @Test
    public void test14(){
        Teacher teacher = new Teacher("10666", "test2", 50, "男", "10548547785");
        Teacher teacher2 = new Teacher("11166", "test2", 50, "男", "10548547785");
        Teacher teacher3 = new Teacher("12666", "test2", 50, "男", "10548547785");
        boolean b = teacherService.addTeacher(teacher);
        boolean b2 = teacherService.addTeacher(teacher2);
        boolean b3 = teacherService.addTeacher(teacher3);
        System.out.println(b+"\n"+b2+"\n"+b3);
    }

    //测试批量删除
    @Test
    public void test22(){
        int[] allId = {10,11,12};
        boolean b = teacherService.batchDelTeacher(allId);
        System.out.println(b);
    }

    @Test
    public void searchStdTest(){
        List<Student> students = studentDao.fuzzyQueryStd("龙");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void searchClsTest(){
        List<Classes> list = classesDao.fuzzyQueryCls("1");
        for (Classes cls : list) {
            System.out.println(cls);
        }
    }

    @Test
    public void searchTeaTest(){
        List<Teacher> list = teacherDao.fuzzyQueryTea("啊啊");
        for (Teacher teacher : list) {
            System.out.println(teacher);
        }
    }

}

package com.mcl.dao.impl;

import com.mcl.dao.UserDao;
import com.mcl.entity.User;
import com.mcl.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User findByUsernameAndPassword(User user) {
        //使用 QueryRunner 工具包
        // 创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        //写 sql
        String sql = "select * from tb_user where username=? and password=?";
        //执行 sql
        User u = null;
        try {
            u = queryRunner.query(sql, new BeanHandler<>(User.class), user.getUsername(), user.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // 使用正常连接
//        //获取连接
//        Connection connection = DruidUtils.getConnection();
//        // 写预编译 sql 语句
//        String sql = "select * from tb_user where username=? and password=?";
//        User u = null;
//        try {
//            // 执行 sql 语句
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            // 给 ? 赋值
//            preparedStatement.setString(1,user.getUsername());
//            preparedStatement.setString(2,user.getPassword());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            u = null;
//            if (resultSet.next()){
//                int id = resultSet.getInt("id");
//                String username = resultSet.getString("username");
//                String password = resultSet.getString("password");
//                u = new User(id,username, password);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        return u;
    }

    @Override
    public boolean addUser(User user) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "insert into tb_user(username,password) values(?,?)";
        int update = 0;
        try {
            update = queryRunner.update(sql, user.getUsername(), user.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update>0;
    }

    @Override
    public User findByUsername(String username) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_user where username=?";
        User query = null;
        try {
            query = queryRunner.query(sql, new BeanHandler<>(User.class), username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;
    }
}

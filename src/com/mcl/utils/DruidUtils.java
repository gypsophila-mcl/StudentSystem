package com.mcl.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
public class DruidUtils {

    //定义变量保存数据源
    private static DataSource ds;
    //创建Druid数据源
    static {
        //static 中内容只会执行一次（在加载类的时候执行）
        /*
        // 方式一：new
        DruidDataSource druidDataSource = new DruidDataSource();
        //设置数据库连接信息
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/std_sys");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        //设置连接池信息
        druidDataSource.setInitialSize(10);//初始连接数
        druidDataSource.setMaxActive(20);//最大连接数
        druidDataSource.setMinIdle(3);//最小空闲数(池子里最少有3个连接空着没连)
        ds = druidDataSource;
        */
        //方式二：使用工厂类
        //(1)把配置信息写在外部文件中
        //(2)获取文件输入流对象
        InputStream in = com.mcl.utils.DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        //(3)创建 Properties 对象，加载文件内容
        Properties properties = new Properties();
        try {
            properties.load(in);
            //通过工厂类来创建数据源时要指定配置信息(properties对象，保存所有配置信息)
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取数据库连接
    public static Connection getConnection(){
        try {
            return  ds.getConnection();  //从数据源中获取连接（Connection）
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    //获取数据源
    public static DataSource getDataSource(){
        return ds;
    }

    //释放资源
    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if (connection != null){
                connection.close();
            }
            if (statement != null){
                statement.close();
            }
            if (resultSet != null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

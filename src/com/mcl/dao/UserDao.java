package com.mcl.dao;

import com.mcl.entity.User;

public interface UserDao {
    //根据账号密码查询用户
    User findByUsernameAndPassword(User user);

    //添加用户
    boolean addUser(User user);

    //根据用户名查询用户
    User findByUsername(String username);
}

package com.mcl.service.impl;

import com.mcl.dao.UserDao;
import com.mcl.dao.impl.UserDaoImpl;
import com.mcl.entity.User;
import com.mcl.exception.MyException;
import com.mcl.service.UserService;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    // 登录功能
    @Override
    public boolean login(User user) {
        User u = userDao.findByUsernameAndPassword(user);
        if (u != null){
            //说明已经查询到数据，登陆成功
            return true;
        }
        return false;
    }

    @Override
    public boolean register(User user) {
        User byUsername = userDao.findByUsername(user.getUsername());
        if (byUsername!=null){
            throw new MyException("用户已存在");
        }
        return userDao.addUser(user);
    }
}

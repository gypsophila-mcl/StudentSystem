package com.mcl.service;

import com.mcl.entity.User;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: 与用户交互(处理业务逻辑)
 */
public interface UserService {
    //登录功能
    boolean login(User user);

    //注册功能
    boolean register(User user);
}

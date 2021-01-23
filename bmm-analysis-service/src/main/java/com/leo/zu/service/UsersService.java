package com.leo.zu.service;

import com.leo.zu.entities.User;
import com.leo.zu.request.UserBean;

/**
 * @author leo-zu
 * @create 2021-01-11 22:16
 */
public interface UsersService {
    /**
     * 通过用户名和密码获取用户
     * @param userBean
     * @return
     */
    public User getUsersByNameAndPassword(UserBean userBean);

    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    public User getUserById(String id);
}

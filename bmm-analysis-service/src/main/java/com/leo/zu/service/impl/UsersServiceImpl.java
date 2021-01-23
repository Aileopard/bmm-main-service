package com.leo.zu.service.impl;

import com.leo.zu.dao.UsersDao;
import com.leo.zu.entities.User;
import com.leo.zu.request.UserBean;
import com.leo.zu.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author leo-zu
 * @create 2021-01-20 23:04
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersDao usersDao;

    /**
     * 通过用户名和密码获取用户
     * @param userBean
     * @return
     */
    @Override
    public User getUsersByNameAndPassword(UserBean userBean) {
        return usersDao.getUsersByNameAndPassword(userBean);
    }

    /**
     * 获取用户信息
     * @param id
     * @return user
     */
    @Override
    public User getUserById(String id) {
        return usersDao.getUserById(id);
    }
}

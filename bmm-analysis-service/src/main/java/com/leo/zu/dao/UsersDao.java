package com.leo.zu.dao;

import com.leo.zu.entities.User;
import com.leo.zu.request.UserBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author leo-zu
 * @create 2020-10-03 10:04
 */
@Mapper
public interface UsersDao {

    /**
     * 查询数据库中是否存在该用户名和密码
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

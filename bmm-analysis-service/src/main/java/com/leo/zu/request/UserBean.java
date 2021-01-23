package com.leo.zu.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leo-zu
 * @create 2021-01-23 15:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {
    /**
     * 用户id
     */
    private String id;

    /**
     * 用户密码
     */
    private String password;
}

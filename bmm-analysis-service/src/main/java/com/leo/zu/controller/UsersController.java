package com.leo.zu.controller;

import com.leo.zu.entities.User;
import com.leo.zu.request.UserBean;
import com.leo.zu.service.UsersService;
import com.leopard.springcloud.entities.CommonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author leo-zu
 * @create 2021-01-11 22:15
 */
@RestController
@Slf4j
@CrossOrigin
public class UsersController {
    @Resource
    private UsersService usersService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录",httpMethod = "POST")
    public CommonResult login(@RequestBody UserBean userBean){
        User user = usersService.getUsersByNameAndPassword(userBean);
        log.info("**** 登录 ****" + user.toString());
        if(userBean != null){
            return new CommonResult(200,"登录成功",user);
        }else{
            return new CommonResult(400,"登录失败",null);
        }
    }

    @GetMapping(value = "/getUserById")
    @ApiOperation(value = "获取用户信息",httpMethod = "GET")
    public CommonResult getUserById(@RequestParam(value = "id", required = false, defaultValue = "1") String id){
        User user = usersService.getUserById(id);
        log.info("**** 获取用户信息 ****" + user.toString());
        if(user != null){
            return new CommonResult(200,"登录成功",user);
        }else{
            return new CommonResult(400,"登录失败",null);
        }
    }
}

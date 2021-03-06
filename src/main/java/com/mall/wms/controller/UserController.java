package com.mall.wms.controller;

import com.mall.wms.entity.UserEntity;
import com.mall.wms.mapper.UserMapper;
import com.mall.wms.service.UserService;
import com.mall.wms.vo.GetUserInfoByIdIn;
import com.mall.wms.vo.IsHasUserIn;
import com.mall.wms.vo.JsonOut;
import com.mall.wms.vo.LoginIn;
import com.qiniu.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.mall.wms.vo.JsonOut.ok;


/**
 * @author haonan
 * create on 2020/1/9 14:20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("login")
    public JsonOut login(@RequestBody @Validated LoginIn in){
        return ok(userService.login(in));
    }

    @PostMapping("loginOut")
    public JsonOut loginOut(){
        return ok(userService.loginOut());
    }

    @PostMapping("updateUserInfo")
    public JsonOut updateUserInfo(@RequestBody @Validated UserEntity in){
        return ok(userService.updateUserInfo(in));
    }

    @PostMapping("add")
    public JsonOut addUser(@RequestBody @Validated UserEntity in){
        return ok(userService.addUser(in));
    }

    @PostMapping("getUserInfo")
    public JsonOut getUserInfo(){
        return ok(userService.loginOut());
    }

    @PostMapping("get-user-info-by-id")
    public JsonOut getUserInfoById(@RequestBody @Validated GetUserInfoByIdIn in ){
        return ok(userService.getUserInfoById(in));
    }

    @PostMapping("is-has-user")
    public JsonOut isHasUser(@RequestBody @Validated IsHasUserIn in){
        return ok(userService.isHasUser(in));
    }

    @PostMapping("isLogin")
    public JsonOut isLogin(){
        return ok(userService.isLogin());
    }
}

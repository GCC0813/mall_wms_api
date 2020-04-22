package com.mall.wms.controller;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.service.UserResgiterService;
import com.mall.wms.vo.JsonOut;
import com.mall.wms.vo.UserResiterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mall.wms.vo.JsonOut.ok;

/**
 * @author: haonan
 * @date:2020/3/15 14:21
 */
@RestController
@RequestMapping("/register")
public class UserResgiterController {

    @Autowired
    private UserResgiterService userResgiterService;

    /**
     * 用户注册
     */
    @PostMapping("/userRegister")
    public JsonOut userResgier(@RequestBody @Validated UserResiterIn resiterIn){

        return ok(userResgiterService.resgiter(resiterIn));
    }

}

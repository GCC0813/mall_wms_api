package com.mall.wms.controller;

import com.mall.wms.service.UserManagementService;
import com.mall.wms.vo.EnAndDisIn;
import com.mall.wms.vo.JsonOut;
import com.mall.wms.vo.UserListIn;
import com.mall.wms.vo.UserListOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user-management")
@RestController
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("user-list")
    public JsonOut getUserList(@RequestBody @Validated UserListIn in){
        return new JsonOut<>(userManagementService.getUserList(in));
    }

    @PostMapping("enable-disabled")
    public JsonOut enAndDis(@RequestBody @Validated EnAndDisIn in){
        return new JsonOut<>(userManagementService.enAndDis(in));
    }

}

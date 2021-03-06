package com.mall.wms.controller;

import com.mall.wms.service.UserManagementService;
import com.mall.wms.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mall.wms.vo.JsonOut.ok;

@RequestMapping("user-management")
@RestController
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("user-list")
    public JsonOut getUserList(@RequestBody @Validated UserListIn in){
        return ok(userManagementService.getUserList(in));
    }

    @PostMapping("user-operation")
    public JsonOut userOperation(@RequestBody @Validated UserOperationIn in){
        return ok(userManagementService.userOperation(in));
    }
}

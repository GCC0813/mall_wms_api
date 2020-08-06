package com.mall.wms.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class TestController {

    @PostMapping("test01")
    @ResponseBody
    @RequiresPermissions("asdas")
    public String a(String a){
        return a;
    }
}

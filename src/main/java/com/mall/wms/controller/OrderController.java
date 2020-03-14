package com.mall.wms.controller;

import com.mall.wms.vo.JsonOut;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GCC
 * create on 2020/3/6 13:46
 */
@RequestMapping("order")
@RestController
public class OrderController {

    @PostMapping("add")
    public JsonOut add(){
        return null;
    }

    @PostMapping("list")
    public JsonOut list(){
        return null;
    }

    @PostMapping("details")
    public JsonOut details(){
        return null;
    }

}

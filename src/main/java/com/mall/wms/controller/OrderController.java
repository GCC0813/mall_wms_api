package com.mall.wms.controller;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.mapper.UserOrderMapper;
import com.mall.wms.service.OrderService;
import com.mall.wms.vo.ChangeOrderStatusIn;
import com.mall.wms.vo.JsonOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.mall.wms.vo.JsonOut.ok;

/**
 * @author GCC
 * create on 2020/3/6 13:46
 */
@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("add")
    public JsonOut add(){
        return ok(null);
    }

    @PostMapping("order-list")
    public JsonOut orderList(){
        return ok(orderService.orderList());
    }

    @PostMapping("details")
    public JsonOut details(){
        return ok(null);
    }

    @PostMapping("change-status")
    public JsonOut changeStatus(@RequestBody @Validated ChangeOrderStatusIn in){
        return ok(CodeMsg.CODE_200);
    }

    @PostMapping("to-deliver-goods")
    public JsonOut toDeliverGoods(){
        return ok(CodeMsg.CODE_200);
    }
}

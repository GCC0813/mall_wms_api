package com.mall.wms.controller;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.service.OrderService;
import com.mall.wms.vo.ChangeOrderStatusIn;
import com.mall.wms.vo.JsonOut;
import com.mall.wms.vo.OrderDetailsIn;
import com.mall.wms.vo.OrderToDeliverIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mall.wms.comm.CodeMsg.CODE_200;
import static com.mall.wms.vo.JsonOut.ok;

/**
 * @author haonan
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

    @PostMapping("list")
    public JsonOut orderList(){
        return ok(orderService.orderList());
    }

    @PostMapping("details")
    public JsonOut details(@RequestBody @Validated OrderDetailsIn in){
        return ok(orderService.orderDetails(in));
    }

    @PostMapping("change-status")
    public JsonOut changeStatus(@RequestBody @Validated ChangeOrderStatusIn in){
        return ok(CODE_200);
    }

    @PostMapping("to-deliver")
    public JsonOut toDeliverGoods(@RequestBody @Validated OrderToDeliverIn in){
        return ok(orderService.toDeliverGoods(in));
    }

    @PostMapping("goods-details")
    public JsonOut goodsDetails(@RequestBody @Validated OrderDetailsIn in){
        return ok(orderService.goodsDetails(in));
    }

    @PostMapping("logistics-details")
    public JsonOut logisticsDetails(@RequestBody @Validated OrderDetailsIn in){
        return ok(orderService.logisticsDetails(in));
    }
}

package com.mall.wms.controller;

import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.service.GoodsService;
import com.mall.wms.vo.JsonOut;
import com.mall.wms.vo.GoodsAuditListIn;
import com.mall.wms.vo.GoodsDetailsIn;
import com.mall.wms.vo.GoodsToExamineIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GCC
 * create on 2020/3/6 13:43
 */
@RequestMapping("goods")
@RestController
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 添加商品
     */
    @PostMapping("add")
    public JsonOut add(@RequestBody @Validated GoodsEntity in){
        return new JsonOut<>(goodsService.addGoods(in));
    }


    /**
     * 更改商品信息
     */
    @PostMapping("change-info")
    public JsonOut changeGoodsInfo(@RequestBody @Validated GoodsEntity in){
        return new JsonOut<>(goodsService.changeGoodsInfo(in));
    }


    /**
     * 商品详情
     */
    @PostMapping("details")
    public JsonOut goodsDetails(@RequestBody @Validated GoodsDetailsIn in){
        return null;
    }


    /**
     * 待审核商品列表和已审核列表
     */
    @PostMapping("goods-list")
    public JsonOut goodsList(@RequestBody @Validated GoodsAuditListIn in){
        return new JsonOut<>(goodsService.goodsList(in));
    }

    /**
     * 商品审核成功或取消审核成功
     */
    @PostMapping("set-goods-status")
    public JsonOut setGoodsStatus(@RequestBody @Validated GoodsToExamineIn in){
        return new JsonOut<>(goodsService.setGoodsStatus(in)) ;
    }



}

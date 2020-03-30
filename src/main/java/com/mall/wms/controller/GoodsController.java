package com.mall.wms.controller;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.service.GoodsService;
import com.mall.wms.vo.*;
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
        return JsonOut.ok(goodsService.goodsDetails(in));
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

    /**
     * 商品链表
     */
    @PostMapping("goodsList")
    public JsonOut getGoodsList(){
        return new JsonOut<>(goodsService.getGoodsList());
    }

    /**
     * 商品名字搜索
     */
    @PostMapping("goodsName")
    public JsonOut getGoodFormName(@RequestBody @Validated GoodNameIn goodNameIn){
        return new JsonOut<>(goodsService.getGoodName(goodNameIn));
    }

    /**
     * 商品总类
     */
    @PostMapping("goodsVariety")
    public JsonOut getGoodsVariety(){
          return new JsonOut<>(goodsService.getGoodsVariety());
    }

    /**
     * 根据标签查商品
     */
    @PostMapping("/getGoodsByTag")
    public JsonOut getGoodsByTag(@RequestBody @Validated GoodTagIn goodTagIn){
        return new JsonOut(goodsService.getGoodsByTag(goodTagIn));
    }

    /**
     * 根据商品种类查商品
     */
    @PostMapping("/getGoodsByVriety")
    public JsonOut getGoodsByVriety(@RequestBody @Validated GoodCategoryIn goodCategoryIn){
        return new JsonOut(goodsService.getGoodsByVriety(goodCategoryIn));
    }

    /**
     * 商品收藏
     */
    @PostMapping("/goodsCollect")
    public JsonOut goodsCollect(@RequestBody @Validated GoodCollectIn goodCollectIn){
        goodsService.goodsCollect(goodCollectIn);
        return new JsonOut(CodeMsg.CODE_200);
    }

    /**
     * 取消商品收藏
     */
    @PostMapping("/goodsDelectCollect")
    public JsonOut goodsDelectCollect(@RequestBody @Validated GoodCollectIn goodCollectIn){
        goodsService.goodsDelectCollect(goodCollectIn);
        return new JsonOut(CodeMsg.CODE_200);
    }

    /**
     * 用户的收藏列表
     */
    @PostMapping("/goodsCollectList")
    public JsonOut goodsCollectList(@RequestBody @Validated UserIn userIn){
         return new JsonOut(goodsService.goodsCollectList(userIn));
    }
}


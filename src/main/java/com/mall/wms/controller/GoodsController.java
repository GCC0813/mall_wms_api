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

import java.util.Collections;

import static com.mall.wms.vo.JsonOut.ok;

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
        return ok(goodsService.addGoods(in));
    }


    /**
     * 更改商品信息
     */
    @PostMapping("change-info")
    public JsonOut changeGoodsInfo(@RequestBody @Validated GoodsEntity in){
        return ok(goodsService.changeGoodsInfo(in));
    }


    /**
     * 商品详情
     */
    @PostMapping("details")
    public JsonOut goodsDetails(@RequestBody @Validated GoodsDetailsIn in){
        return ok(goodsService.goodsDetails(in));
    }


    @PostMapping("details/goodsVariety")
    public JsonOut detailsGoodsVariety(@RequestBody @Validated DetailsGoodsVarietyIn in){
        return null;
    }


    /**
     * 商品列表（包含搜索）
     */
    @PostMapping("goods-list")
    public JsonOut goodsList(@RequestBody @Validated GoodsAuditListIn in){
        return ok(goodsService.goodsList(in));
    }

    /**
     * 商品审核成功或取消审核成功
     */
    @PostMapping("set-goods-status")
    public JsonOut setGoodsStatus(@RequestBody @Validated GoodsToExamineIn in){
        return ok(goodsService.setGoodsStatus(in)) ;
    }

    /**
     * 商品链表
     */
    @PostMapping("goodsList")
    public JsonOut getGoodsList(){
        return ok(goodsService.getGoodsList());
    }

    /**
     * 商品名字搜索
     */
    @PostMapping("goodsName")
    public JsonOut getGoodFormName(@RequestBody @Validated GoodNameIn goodNameIn){
        return ok(goodsService.getGoodName(goodNameIn));
    }

    @PostMapping("/goodsVarietyAdmin")
    public JsonOut getGoodsVarietyAdmin(){
        return ok(Collections.singletonMap("goodVrietyOut",goodsService.getGoodsVariety(1)));
    }
    
    @PostMapping("goodsSupplier")
    public JsonOut getGoodsSupplier(){
        return ok(null);
    }

    /**
     * 商品总类
     */
    @PostMapping("/goodsVariety")
    public JsonOut getGoodsVariety(){
          return ok(Collections.singletonMap("goodVrietyOut",goodsService.getGoodsVariety(2)));
    }

    /**
     * 根据标签查商品
     */
    @PostMapping("/getGoodsByTag")
    public JsonOut getGoodsByTag(@RequestBody @Validated GoodTagIn goodTagIn){
        return ok(goodsService.getGoodsByTag(goodTagIn));
    }

    /**
     * 根据商品种类查商品
     */
    @PostMapping("/getGoodsByVriety")
    public JsonOut getGoodsByVriety(@RequestBody @Validated GoodCategoryIn goodCategoryIn){
        return ok(goodsService.getGoodsByVriety(goodCategoryIn));
    }

    /**
     * 商品收藏
     */
    @PostMapping("/goodsCollect")
    public JsonOut goodsCollect(@RequestBody @Validated GoodCollectIn goodCollectIn){
        goodsService.goodsCollect(goodCollectIn);
        return ok(CodeMsg.CODE_200);
    }

    /**
     * 取消商品收藏
     */
    @PostMapping("/goodsDelectCollect")
    public JsonOut goodsDelectCollect(@RequestBody @Validated GoodCollectIn goodCollectIn){
        goodsService.goodsDelectCollect(goodCollectIn);
        return ok(CodeMsg.CODE_200);
    }

    /**
     * 用户的收藏列表
     */
    @PostMapping("/goodsCollectList")
    public JsonOut goodsCollectList(@RequestBody @Validated UserIn userIn){
         return ok(goodsService.goodsCollectList(userIn));
    }
}


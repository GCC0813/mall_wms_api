package com.mall.wms.service;

import com.github.pagehelper.PageHelper;
import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.GoodsCategoryEntity;
import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.entity.GoodsTagEntity;
import com.mall.wms.mapper.GoodsCategoryMapper;
import com.mall.wms.mapper.GoodsMapper;
import com.mall.wms.mapper.GoodsTagMapper;
import com.mall.wms.vo.*;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.mall.wms.comm.CodeMsg.*;

/**
 * @author GCC
 * create on 2020/3/12 10:35
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private GoodsTagMapper goodsTagMapper;

    public CodeMsg addGoods(GoodsEntity in){
        int row  = goodsMapper.insertSelective(in);
        if(row<1){
            throw new BizException(CODE_209);
        }
        return CODE_200;
    }

    public CodeMsg changeGoodsInfo(GoodsEntity in){
        int row = goodsMapper.updateByPrimaryKeySelective(in);
        if(row<1){
            throw new BizException(CODE_211);
        }
        return CODE_200;
    }


    public List<GoodsEntity> goodsList(GoodsAuditListIn in){
        PageHelper.startPage(in.getPageNo(),in.getPageSize());
        return goodsMapper.selectByCondition(in);
    }


    public CodeMsg setGoodsStatus(GoodsToExamineIn in){
        int row = goodsMapper.updateByType(in);
        if(row < 1){
            throw new BizException(CODE_210);
        }
        return CODE_200;
    }

    public GoodsEntity goodsDetails(GoodsDetailsIn in){
        GoodsEntity entity = goodsMapper.selectByPrimaryKey(in.getGoodsId());
        if(Objects.isNull(entity)){
            throw new BizException(CODE_304);
        }
        return entity;
    }

    /**
     * 商品链表
     * @return
     */
    public List<GoodsEntity> getGoodsList(){
        List<GoodsEntity> entityList = goodsMapper.selectGetGoodsList();
        if(Objects.isNull(entityList)){
            throw new BizException(CODE_304);
        }
        return entityList;
    }

    /**
     * 商品名字搜索
     */
    public List<GoodsEntity> getGoodName(GoodNameIn in){
        List<GoodsEntity> entityList = goodsMapper.selectGoodsByName(in.getGoodName());
        if(CollectionUtils.isEmpty(entityList)){
            throw new BizException(CODE_304);
        }
        return entityList;
    }

    /**
     * 商品种类
     */
    public List<GoodVrietyOut> getGoodsVariety(){
        List<GoodVrietyOut> goodVrietyOutList = new ArrayList<>();
        List<GoodsCategoryEntity> goodsCategoryEntities = goodsCategoryMapper.selectByPrimaryKeyList();
        List<GoodsTagEntity> goodsTagEntityList = goodsTagMapper.selectByPrimaryKeyList();
        if(CollectionUtils.isEmpty(goodsCategoryEntities) || CollectionUtils.isEmpty(goodsTagEntityList)){
            throw new BizException(CODE_305);
        }
        else {
            for (GoodsCategoryEntity categoryEntity : goodsCategoryEntities) {
                List<GoodTagsOut> goodTagsOutList = new ArrayList<>();
                GoodVrietyOut goodVrietyOut = new GoodVrietyOut();
                goodVrietyOut.setVrietyId(categoryEntity.getId());
                goodVrietyOut.setVrietyName(categoryEntity.getName());
                for (GoodsTagEntity tagEntity : goodsTagEntityList) {
                    if (categoryEntity.getId().equals(tagEntity.getCategoryId())) {
                        GoodTagsOut goodTagsOut = new GoodTagsOut();
                        goodTagsOut.setTagId(tagEntity.getId());
                        goodTagsOut.setTagName(tagEntity.getName());
                        goodTagsOutList.add(goodTagsOut);
                    }
                }
                goodVrietyOut.setGoodTagsList(goodTagsOutList);
                goodVrietyOutList.add(goodVrietyOut);
            }
        }
        return goodVrietyOutList;
    }

    /**
     * 根据标签查商品
     */

    public List<GoodsEntity> getGoodsByTag(GoodTagIn goodTagIn){
        List<GoodsEntity> goodsEntityList = goodsMapper.selectGoodsByTag(goodTagIn.getTagId());
        if(CollectionUtils.isEmpty(goodsEntityList)){
            throw new BizException(CODE_305);
        }
        return goodsEntityList;
    }

    /**
     * 根据商品种类查商品
     */
    public List<GoodsEntity> getGoodsByVriety(GoodCategoryIn goodCategoryIn){
        List<GoodsEntity> goodsEntityList = goodsMapper.selectGoodsByVriety(goodCategoryIn.getVarietyId());
        if(CollectionUtils.isEmpty(goodsEntityList)){
            throw new BizException(CODE_305);
        }
        return goodsEntityList;
    }
}

package com.mall.wms.service;

import com.github.pagehelper.PageHelper;
import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.GoodsCategoryEntity;
import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.entity.GoodsTagEntity;
import com.mall.wms.entity.UserCollectEntity;
import com.mall.wms.mapper.GoodsCategoryMapper;
import com.mall.wms.mapper.GoodsMapper;
import com.mall.wms.mapper.GoodsTagMapper;
import com.mall.wms.mapper.UserCollectMapper;
import com.mall.wms.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
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

    @Autowired
    private UserCollectMapper userCollectMapper;

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
            throw new BizException(CODE_307);
        }
        return goodsEntityList;
    }

    /**
     * 根据商品种类查商品
     */
    public List<GoodsEntity> getGoodsByVriety(GoodCategoryIn goodCategoryIn){
        List<GoodsEntity> goodsEntityList = goodsMapper.selectGoodsByVriety(goodCategoryIn.getVarietyId());
        if(CollectionUtils.isEmpty(goodsEntityList)){
            throw new BizException(CODE_307);
        }
        return goodsEntityList;
    }
    /**
     * 商品收藏
     */
    public void goodsCollect(GoodCollectIn in){
        //先去查询该用户有没有收藏过该商品
       UserCollectEntity userCollectEntity  = userCollectMapper.selectByUserIdAndGoodIds(in.getUserId(),in.getGoodsId());
       if(!StringUtils.isEmpty(userCollectEntity)){
           throw new BizException(CODE_403);
       }
        UserCollectEntity entity = new UserCollectEntity();
        entity.setUserId(in.getUserId());
        entity.setGoodsId(in.getGoodsId());
        try {
            userCollectMapper.insertSelective(entity);
        } catch (Exception e) {
            throw new BizException(CODE_401);
        }
    }

    /**
     * 取消商品收藏
     */
    public void goodsDelectCollect(GoodCollectIn in){
        try {
            userCollectMapper.deleteByUserIdAndGoodIds(in.getUserId(),in.getGoodsId());
        } catch (Exception e) {
            throw new BizException(CODE_304);
        }
    }

    /**
     * 查询收藏列表
     */
    public List<GoodsEntity> goodsCollectList(UserIn in){
        List<GoodsEntity> goodsEntityList = new ArrayList<>();
         //先查询用户收藏的商品id
        List<Integer> goodsIds = userCollectMapper.selectByUserId(in.getUserId());
        if(CollectionUtils.isEmpty(goodsIds)){
            throw new BizException(CODE_304);
        }
        goodsEntityList = goodsMapper.selectGoodsByIds(goodsIds);
        if(CollectionUtils.isEmpty(goodsEntityList)){
            throw new BizException(CODE_307);
        }
        return goodsEntityList;
    }
}

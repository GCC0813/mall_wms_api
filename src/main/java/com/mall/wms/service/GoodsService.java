package com.mall.wms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.RedisOperation;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.*;
import com.mall.wms.mapper.*;
import com.mall.wms.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.mall.wms.comm.CodeMsg.*;
import static com.mall.wms.comm.GlobalVar.GOODS_REDIS_KEY;
import static com.mall.wms.enums.GoodsEnums.STATIC_CATEGORY;
import static com.mall.wms.enums.GoodsEnums.STATIC_TAGS;
import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MINUTES;

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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    GoodsSupplierMapper goodsSupplierMapper;

    @Autowired
    HttpSession httpSession;


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

    public GoodsListOut goodsList(GoodsAuditListIn in){
        List<GoodsCategoryEntity> goodsCategoryEntities = goodsCategoryMapper.selectByPrimaryKeyList();
        List<GoodsTagEntity> goodsTagEntityList = goodsTagMapper.selectByPrimaryKeyList();
        Map<Integer,GoodsCategoryEntity> goodsCategoryEntityMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(goodsCategoryEntities)){
            goodsCategoryEntityMap = goodsCategoryEntities.stream().collect(Collectors.toMap(GoodsCategoryEntity::getId, Function.identity()));
        }
        Map<Integer,GoodsTagEntity> goodsTagEntityMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(goodsTagEntityList)){
            goodsTagEntityMap=goodsTagEntityList.stream().collect(Collectors.toMap(GoodsTagEntity::getId, Function.identity()));
        }
        Page page = PageHelper.startPage(in.getCurrentPage(),in.getPageSize());
        List<GoodsEntity> goodsBySql = goodsMapper.selectByCondition(in);
        List<GoodsListOut.GoodsOut> goodsOuts = new ArrayList<>();
        if(!CollectionUtils.isEmpty(goodsBySql)){
            for (GoodsEntity goods:goodsBySql){
                goodsOuts.add(new GoodsListOut.GoodsOut(goods,goodsCategoryEntityMap,goodsTagEntityMap));
            }
        }
        return new GoodsListOut(page.getTotal(),goodsOuts);
    }

    public CodeMsg setGoodsStatus(GoodsToExamineIn in){
        GoodsEntity goodsEntity = goodsMapper.selectByPrimaryKey(in.getGoodsId());
        if(Objects.isNull(goodsEntity)){
            throw new BizException(CODE_213);
        }
        Long userId = (long) ((UserEntity)httpSession.getAttribute("user")).getId();
        in.setUserId(userId);
        int row = goodsMapper.updateStatusByType(in);
        if(row < 1){
            throw new BizException(CODE_210);
        }
        return CODE_200;
    }

    public GoodsDetailsOut goodsDetails(GoodsDetailsIn in) {
        GoodsEntity entity = goodsMapper.selectByPrimaryKey(in.getGoodsId());
        if (Objects.isNull(entity)) {
            throw new BizException(CODE_304);
        }

        GoodsTagEntity goodsTagEntity = goodsTagMapper.selectByPrimaryKey(entity.getTagId());

        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryMapper.selectByPrimaryKey(entity.getCategoryId());

        List<Long> userIds = new ArrayList<Long>() {{
            add(entity.getCheckBy());
            add(entity.getCreateBy());
            add(entity.getUpdateBy());
        }};

        GoodsSupplierEntity goodsSupplierEntity = goodsSupplierMapper.selectByPrimaryKey(entity.getSupplierId());

        List<UserEntity> userEntitieList = userMapper.selectUserListByIds(userIds,0);
        Map<Integer, UserEntity> userEntityHashMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(userEntitieList)) {
            userEntityHashMap = userEntitieList.stream().collect(Collectors.toMap(UserEntity::getId, Function.identity()));
        }
        return new GoodsDetailsOut(entity, goodsTagEntity, goodsCategoryEntity, userEntityHashMap,goodsSupplierEntity);
    }

    /**
     * 商品链表
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
    public List<GoodVrietyOut> getGoodsVariety(int from){
        List<GoodVrietyOut> goodVrietyOutList = new ArrayList<>();
        List<GoodsCategoryEntity> goodsCategoryEntities = goodsCategoryMapper.selectByPrimaryKeyList();
        List<GoodsTagEntity> goodsTagEntityList = goodsTagMapper.selectByPrimaryKeyList();
        if(CollectionUtils.isEmpty(goodsCategoryEntities) || CollectionUtils.isEmpty(goodsTagEntityList)){
            throw new BizException(CODE_305);
        }
        else {
            if(from==1){
                goodVrietyOutList.add(new GoodVrietyOut(STATIC_CATEGORY,new ArrayList<GoodTagsOut>(){{
                    add(new GoodTagsOut(STATIC_TAGS));
                }}));
            }
            for (GoodsCategoryEntity categoryEntity : goodsCategoryEntities) {
                List<GoodTagsOut> goodTagsOutList = new ArrayList<>();
                GoodVrietyOut goodVrietyOut = new GoodVrietyOut();
                goodVrietyOut.setVrietyId(categoryEntity.getId());
                goodVrietyOut.setVrietyName(categoryEntity.getName().trim());
                for (GoodsTagEntity tagEntity : goodsTagEntityList) {
                    if (categoryEntity.getId().equals(tagEntity.getCategoryId())) {
                        GoodTagsOut goodTagsOut = new GoodTagsOut();
                        goodTagsOut.setTagId(tagEntity.getId());
                        goodTagsOut.setTagName(tagEntity.getName().trim());
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

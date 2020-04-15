package com.mall.wms.service;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.entity.GoodsCategoryEntity;
import com.mall.wms.entity.GoodsTagEntity;
import com.mall.wms.mapper.GoodsCategoryMapper;
import com.mall.wms.mapper.GoodsTagMapper;
import com.mall.wms.vo.CategoryTagListOut;
import com.mall.wms.vo.ModifyCategoryOrTagStatusIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;
import java.util.stream.Collectors;

import static com.mall.wms.comm.CodeMsg.*;

@Service
public class CategoryTagService {

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    GoodsTagMapper goodsTagMapper;

    public CategoryTagListOut categoryTagList(){
        List<GoodsCategoryEntity> goodsCategories = goodsCategoryMapper.selectAll();
        List<CategoryTagListOut.CategoryTagOut> categoryTagList = new ArrayList<>(goodsCategories.size());
        if(!CollectionUtils.isEmpty(goodsCategories)){
            List<Integer>goodsCateIds = goodsCategories.stream().map(GoodsCategoryEntity::getId).collect(Collectors.toList());
            List<GoodsTagEntity> goodsTags = goodsTagMapper.selectByCateIds(goodsCateIds);
            MultiValueMap<Integer,CategoryTagListOut.CategoryTag> multiValueMap = new LinkedMultiValueMap<>();
            goodsTags.forEach(g->multiValueMap.add(g.getCategoryId(),new CategoryTagListOut.CategoryTag(g)));
            goodsCategories.forEach(g->categoryTagList.add(new CategoryTagListOut.CategoryTagOut(g,multiValueMap)));
        }
        return new CategoryTagListOut(categoryTagList);
    }


    public CodeMsg modifyCategoryOrTagStatus(ModifyCategoryOrTagStatusIn in){
        //分类
        if(in.getType()==1){


        //标签
        }else if(in.getType()==2){

        }

        return CODE_200;
    }

}

package com.mall.wms.service;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.GoodsCategoryEntity;
import com.mall.wms.entity.GoodsTagEntity;
import com.mall.wms.mapper.GoodsCategoryMapper;
import com.mall.wms.mapper.GoodsTagMapper;
import com.mall.wms.vo.CategoryTagListOut;
import com.mall.wms.vo.ModifyCategoryOrTagStatusIn;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


    @Transactional(rollbackFor = BizException.class)
    public CodeMsg modifyCategoryOrTagStatus(ModifyCategoryOrTagStatusIn in){
        //特殊情况，停用分类，分类下的标签都要停用
        if(in.getType()==1 && in.getStatusType()==1 && in.getStatus()==0){
            int row = goodsCategoryMapper.modifyStatus(in);
            if(row>0){
                int ro = goodsTagMapper.modifyStatusByCateId(in);
                if(ro<1){
                    throw new BizException(CODE_603);
                }
            }else {
                throw new BizException(CODE_602);
            }
        //类型删除需要删除下面标签
        }else if(in.getType()==1 && in.getStatusType()==3 && in.getStatus()==1){
            int row = goodsCategoryMapper.modifyStatus(in);
            if(row>0){
                int ro = goodsTagMapper.modifyStatusByCateId(in);
                if(ro<1){
                    throw new BizException(CODE_603);
                }
            }else {
                throw new BizException(CODE_602);
            }
        }else
        //分类
        if(in.getType()==1){
            int row = goodsCategoryMapper.modifyStatus(in);
            if(row<1){
                throw new BizException(CODE_602);
            }
        //标签
        }else if(in.getType()==2){
            int row = goodsTagMapper.modifyStatus(in);
            if(row<1){
                throw new BizException(CODE_603);
            }
        }
        return CODE_200;
    }


    @RabbitListener(queues = "qqq",containerFactory = "oneFactory")
    public void over(Message message){
        System.out.println(new String(message.getBody()));
    }


}

package com.mall.wms.service;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.GoodsCategoryEntity;
import com.mall.wms.entity.GoodsTagEntity;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.mapper.GoodsCategoryMapper;
import com.mall.wms.mapper.GoodsTagMapper;
import com.mall.wms.vo.*;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

import static com.mall.wms.comm.CodeMsg.*;
import static com.mall.wms.comm.exceptionhandler.BizException.bizException;

@Service
public class CategoryTagService {

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    GoodsTagMapper goodsTagMapper;

    @Autowired
    HttpSession httpSession;

    private final UserEntity userEntity = (UserEntity) httpSession.getAttribute("user");


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
                    throw bizException(CODE_603);
                }
            }else {
                throw bizException(CODE_602);
            }
        //类型删除需要删除下面标签
        }else if(in.getType()==1 && in.getStatusType()==3 && in.getStatus()==1){
            int row = goodsCategoryMapper.modifyStatus(in);
            if(row>0){
                int ro = goodsTagMapper.modifyStatusByCateId(in);
                if(ro<1){
                    throw bizException(CODE_603);
                }
            }else {
                throw bizException(CODE_602);
            }
        }else
        //分类
        if(in.getType()==1){
            int row = goodsCategoryMapper.modifyStatus(in);
            if(row<1){
                throw bizException(CODE_602);
            }
        //标签
        }else if(in.getType()==2){
            int row = goodsTagMapper.modifyStatus(in);
            if(row<1){
                throw bizException(CODE_603);
            }
        }
        return CODE_200;
    }

    public JsonOut categoryTagInfo(ModifyCategoryOrTagStatusIn in){
        if (in.getType()==1){
            GoodsCategoryEntity goodsCategoryEntity = goodsCategoryMapper.selectByPrimaryKey(in.getId());
            if(Objects.isNull(goodsCategoryEntity)){
                throw bizException(CODE_604);
            }
            return JsonOut.ok(goodsCategoryEntity);
        }else {
            GoodsTagEntity goodsTagEntity = goodsTagMapper.selectByPrimaryKey(in.getId());
            if(Objects.isNull(goodsTagEntity)){
                throw bizException(CODE_605);
            }
            return JsonOut.ok(goodsTagEntity);
        }
    }

    public CodeMsg editCategoryTagInfo(EditCategoryTagInfoIn in){
        if(in.getType()==1){
            GoodsCategoryEntity goodsCategoryEntity =new GoodsCategoryEntity();
            goodsCategoryEntity.setId(in.getId());
            goodsCategoryEntity.setName(in.getName());
            goodsCategoryEntity.setRemark(in.getRemark());
            int row = goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategoryEntity);
            if(row<1){
                throw bizException(CODE_606);
            }
        }else {
            GoodsTagEntity goodsTagEntity =new GoodsTagEntity();
            goodsTagEntity.setId(in.getId());
            goodsTagEntity.setName(in.getName());
            goodsTagEntity.setRemark(in.getRemark());
            int row = goodsTagMapper.updateByPrimaryKeySelective(goodsTagEntity);
            if(row<1){
                throw bizException(CODE_607);
            }
        }
        return CODE_200;
    }

    public CodeMsg addTag(AddTagIn in){
        GoodsTagEntity goodsTagEntity = new GoodsTagEntity();
        goodsTagEntity.setCategoryId(in.getCateId());
        goodsTagEntity.setName(in.getTagName());
        goodsTagEntity.setRemark(in.getTagRemark());
        goodsTagEntity.setCreateBy(userEntity.getId().longValue());
        int rows = goodsTagMapper.insertSelective(goodsTagEntity);
        if (rows<1){
            throw bizException(CODE_608);
        }
        return CODE_200;

    }

    public CodeMsg addCate(AddCateIn in){
        String cateName = in.getCateName().replaceAll(" ","");
        if(cateName.length()>10){
            return CODE_609;
        }
        if(!cateName.contains("类")){
            cateName=cateName+"类";
        }
        String cateRemark = in.getCateRemark().replaceAll(" ","");
        if(cateRemark.length()>30){
            return CODE_610;
        }
        GoodsCategoryEntity goodsCategoryEntity = new GoodsCategoryEntity();
        goodsCategoryEntity.setName(cateName);
        goodsCategoryEntity.setRemark(cateRemark);
        goodsCategoryEntity.setCheckStatus(in.getCheckStatus().byteValue());
        goodsCategoryEntity.setStatus(in.getStatus());
        //goodsCategoryEntity.setCreateBy(userEntity.getId().longValue());
        int rows = goodsCategoryMapper.insertSelective(goodsCategoryEntity);
        if (rows<1){
            throw bizException(CODE_611);
        }
        return CODE_200;
    }



    @RabbitListener(queues = "qqq",containerFactory = "oneFactory")
    public void over(Message message){
        System.out.println(new String(message.getBody()));
    }


}

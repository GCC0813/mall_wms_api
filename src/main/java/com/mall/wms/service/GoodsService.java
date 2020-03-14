package com.mall.wms.service;

import com.github.pagehelper.PageHelper;
import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.mapper.GoodsMapper;
import com.mall.wms.vo.GoodsAuditListIn;
import com.mall.wms.vo.GoodsToExamineIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.mall.wms.comm.CodeMsg.*;

/**
 * @author GCC
 * create on 2020/3/12 10:35
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

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

}

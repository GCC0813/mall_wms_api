package com.mall.wms.mapper;

import com.mall.wms.entity.GoodsTagEntity;

import java.util.List;

public interface GoodsTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsTagEntity record);

    int insertSelective(GoodsTagEntity record);

    GoodsTagEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsTagEntity record);

    int updateByPrimaryKey(GoodsTagEntity record);

    List<GoodsTagEntity>  selectByPrimaryKeyList();
}
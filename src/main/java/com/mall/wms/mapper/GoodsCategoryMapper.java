package com.mall.wms.mapper;

import com.mall.wms.entity.GoodsCategoryEntity;

import java.util.List;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsCategoryEntity record);

    int insertSelective(GoodsCategoryEntity record);

    GoodsCategoryEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsCategoryEntity record);

    int updateByPrimaryKey(GoodsCategoryEntity record);

    List<GoodsCategoryEntity> selectByPrimaryKeyList();
}
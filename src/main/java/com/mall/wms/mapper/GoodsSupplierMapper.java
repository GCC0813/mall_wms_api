package com.mall.wms.mapper;

import com.mall.wms.entity.GoodsSupplierEntity;

import java.util.List;

public interface GoodsSupplierMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSupplierEntity record);

    int insertSelective(GoodsSupplierEntity record);

    GoodsSupplierEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsSupplierEntity record);

    int updateByPrimaryKey(GoodsSupplierEntity record);

    List<GoodsSupplierEntity> selectAll();
}
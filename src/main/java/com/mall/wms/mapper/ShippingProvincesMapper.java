package com.mall.wms.mapper;

import com.mall.wms.entity.ShippingProvincesEntity;

public interface ShippingProvincesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShippingProvincesEntity record);

    int insertSelective(ShippingProvincesEntity record);

    ShippingProvincesEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShippingProvincesEntity record);

    int updateByPrimaryKey(ShippingProvincesEntity record);
}
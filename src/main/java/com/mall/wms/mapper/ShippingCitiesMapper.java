package com.mall.wms.mapper;

import com.mall.wms.entity.ShippingCitiesEntity;

public interface ShippingCitiesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShippingCitiesEntity record);

    int insertSelective(ShippingCitiesEntity record);

    ShippingCitiesEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShippingCitiesEntity record);

    int updateByPrimaryKey(ShippingCitiesEntity record);
}
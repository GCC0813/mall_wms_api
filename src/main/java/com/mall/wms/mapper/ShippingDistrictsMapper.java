package com.mall.wms.mapper;

import com.mall.wms.entity.ShippingDistrictsEntity;

public interface ShippingDistrictsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShippingDistrictsEntity record);

    int insertSelective(ShippingDistrictsEntity record);

    ShippingDistrictsEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShippingDistrictsEntity record);

    int updateByPrimaryKey(ShippingDistrictsEntity record);
}
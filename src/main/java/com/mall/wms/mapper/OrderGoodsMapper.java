package com.mall.wms.mapper;

import com.mall.wms.entity.OrderGoodsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderGoodsEntity record);

    int insertSelective(OrderGoodsEntity record);

    OrderGoodsEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderGoodsEntity record);

    int updateByPrimaryKey(OrderGoodsEntity record);

    List<OrderGoodsEntity> selectByOrderIds(@Param("orderIds")List<Long> orderIds);
}
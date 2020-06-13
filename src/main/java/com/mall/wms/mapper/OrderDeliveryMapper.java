package com.mall.wms.mapper;

import com.mall.wms.entity.OrderDeliveryEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDeliveryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDeliveryEntity record);

    int insertSelective(OrderDeliveryEntity record);

    OrderDeliveryEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDeliveryEntity record);

    int updateByPrimaryKey(OrderDeliveryEntity record);

    List<OrderDeliveryEntity> selectOrderDeliveryByOrderIds(@Param("orderIds")List<Long> orderIds);
}
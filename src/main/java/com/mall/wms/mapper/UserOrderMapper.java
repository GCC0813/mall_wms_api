package com.mall.wms.mapper;

import com.mall.wms.entity.UserOrderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserOrderEntity record);

    int insertSelective(UserOrderEntity record);

    UserOrderEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserOrderEntity record);

    int updateByPrimaryKey(UserOrderEntity record);
}
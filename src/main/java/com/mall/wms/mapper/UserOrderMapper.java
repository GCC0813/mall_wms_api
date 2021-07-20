package com.mall.wms.mapper;

import com.mall.wms.entity.UserOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserOrderEntity record);

    int insertSelective(UserOrderEntity record);

    UserOrderEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserOrderEntity record);

    int updateByPrimaryKey(UserOrderEntity record);

    List<UserOrderEntity> selectAll();

    List<UserOrderEntity> selectByDate(@Param("startTime")Long startTime,@Param("endTime")Long enTime);
}
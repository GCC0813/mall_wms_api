package com.mall.wms.mapper;

import com.mall.wms.entity.UserLoginEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Date;

@Mapper
public interface UserLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginEntity record);

    int insertSelective(UserLoginEntity record);

    UserLoginEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLoginEntity record);

    int updateByPrimaryKey(UserLoginEntity record);

    //Date selectLoginTimeByUserId(@Param("userId") Integer id);
}
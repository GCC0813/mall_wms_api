package com.mall.wms.mapper;

import com.mall.wms.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    UserEntity selectByUserAndPassword(@Param("userName")String userName ,@Param("password")String password);

    UserEntity selectAdminByUserNameAndRole(@Param("userName")String userName,@Param("role") Integer role);

    UserEntity selectCustomerByUserNameAndRole(@Param("userName")String userName,@Param("role") Integer role,@Param("type")Integer type);

    Long selectCount();

}
package com.mall.wms.mapper;

import com.mall.wms.entity.UserCollectEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCollectEntity record);

    int insertSelective(UserCollectEntity record);

    UserCollectEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCollectEntity record);

    int updateByPrimaryKey(UserCollectEntity record);

    int deleteByUserIdAndGoodIds(@Param("userId")int userId,@Param("goodsId")int goodsId);

    List<Integer> selectByUserId(@Param("userId")int userId);

    UserCollectEntity selectByUserIdAndGoodIds(@Param("userId")int userId,@Param("goodsId")int goodsId);
}
package com.mall.wms.mapper;

import com.mall.wms.entity.BasSendSmsEntity;

public interface BasSendSmsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasSendSmsEntity record);

    int insertSelective(BasSendSmsEntity record);

    BasSendSmsEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasSendSmsEntity record);

    int updateByPrimaryKey(BasSendSmsEntity record);
}
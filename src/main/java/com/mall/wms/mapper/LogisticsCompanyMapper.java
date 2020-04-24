package com.mall.wms.mapper;

import com.mall.wms.entity.LogisticsCompanyEntity;

public interface LogisticsCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogisticsCompanyEntity record);

    int insertSelective(LogisticsCompanyEntity record);

    LogisticsCompanyEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogisticsCompanyEntity record);

    int updateByPrimaryKey(LogisticsCompanyEntity record);
}
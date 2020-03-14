package com.mall.wms.mapper;

import com.mall.wms.entity.ManagementListEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagementListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManagementListEntity record);

    int insertSelective(ManagementListEntity record);

    ManagementListEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManagementListEntity record);

    int updateByPrimaryKey(ManagementListEntity record);

    List<ManagementListEntity> selectAll();
}
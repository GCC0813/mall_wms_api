package com.mall.wms.mapper;

import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.vo.GoodsAuditListIn;
import com.mall.wms.vo.GoodsToExamineIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsEntity record);

    int insertSelective(GoodsEntity record);

    GoodsEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsEntity record);

    int updateByPrimaryKey(GoodsEntity record);

    List<GoodsEntity> selectByCondition(GoodsAuditListIn in);

    int updateByType(GoodsToExamineIn in);

    Long selectCount();

}
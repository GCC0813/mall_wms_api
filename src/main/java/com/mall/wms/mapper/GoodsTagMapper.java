package com.mall.wms.mapper;

import com.mall.wms.entity.GoodsTagEntity;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface GoodsTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsTagEntity record);

    int insertSelective(GoodsTagEntity record);

    GoodsTagEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsTagEntity record);

    int updateByPrimaryKey(GoodsTagEntity record);

    List<GoodsTagEntity>  selectByPrimaryKeyList();

    List<GoodsTagEntity> selectByCateId(@Param("cateId")Integer id);

}
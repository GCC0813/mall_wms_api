package com.mall.wms.mapper;

import com.mall.wms.entity.GoodsTagEntity;
import com.mall.wms.vo.ModifyCategoryOrTagStatusIn;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;
import java.util.Map;

public interface GoodsTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsTagEntity record);

    int insertSelective(GoodsTagEntity record);

    GoodsTagEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsTagEntity record);

    int updateByPrimaryKey(GoodsTagEntity record);

    List<GoodsTagEntity>  selectByPrimaryKeyList();

    List<GoodsTagEntity> selectByCateId(@Param("cateId")Integer id);

    List<GoodsTagEntity> selectByCateIds(@Param("goodsCateIds") List<Integer>goodsCateIds);

    int modifyStatus(ModifyCategoryOrTagStatusIn in);

    int modifyStatusByCateId(ModifyCategoryOrTagStatusIn in);
}
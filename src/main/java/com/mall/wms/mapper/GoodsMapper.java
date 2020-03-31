package com.mall.wms.mapper;

import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.vo.GoodsAuditListIn;
import com.mall.wms.vo.GoodsToExamineIn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GoodsEntity record);

    GoodsEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsEntity record);

    int updateByPrimaryKey(GoodsEntity record);

    List<GoodsEntity> selectByCondition(GoodsAuditListIn in);

    int updateByType(GoodsToExamineIn in);

    Long selectCount();

    List<GoodsEntity> selectGetGoodsList();

    List<GoodsEntity> selectGoodsByName(@Param("name") String name );

    List<GoodsEntity> selectGoodsByTag(@Param("tagId") int tagId);

    List<GoodsEntity> selectGoodsByVriety(@Param("vrietyId")int vrietyId);

    List<GoodsEntity> selectGoodsByIds(@Param("goodsIds") List<Integer> goodsIds);
}
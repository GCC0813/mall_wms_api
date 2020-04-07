package com.mall.wms.vo;

import com.mall.wms.entity.GoodsCategoryEntity;
import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.entity.GoodsTagEntity;
import com.mall.wms.entity.UserEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.mall.wms.comm.GlobalVar.STATIC_RESOURCES_PREFIX;
import static com.mall.wms.util.DateUtil.*;

@Data
public class GoodsDetailsOut {

    private Long id;

    private String name;

    private List<String> picUrls;

    private List<String> detailPicUrls;

    private String synopsis;

    private String categoryName;

    private String tagName;

    private String physicalOrVirtual;

    private BigDecimal marketPrice;

    private BigDecimal purchasePrice;

    private String supplier;

    private Integer status;

    private String statusStr;

    private Integer checkStatus;

    private String checkStatusStr;

    private String toExamine;

    private String toExamineTime;

    private String founder;

    private String createTime;

    private String modifier;

    private String modificationTime;

    public GoodsDetailsOut() {
    }

    public GoodsDetailsOut(GoodsEntity g, Map<Integer, GoodsTagEntity> goodsTagEntityMap,Map<Integer,
            GoodsCategoryEntity> goodsCategoryEntityMap,Map<Integer, UserEntity> userEntityHashMap) {
        this.id = g.getId();
        this.name = g.getName();
        this.picUrls = (Arrays.asList(g.getPicUrls().split(","))).stream().map(p->String.format(STATIC_RESOURCES_PREFIX,p)).collect(Collectors.toList());
        List<String> detailPics = Arrays.asList(g.getDetailPicUrls().split(","));
        detailPics.forEach(dp-> dp = String.format(STATIC_RESOURCES_PREFIX,dp));
        this.detailPicUrls = detailPics;
        this.synopsis = g.getSynopsis();
        GoodsCategoryEntity category=goodsCategoryEntityMap.get(g.getCategoryId());
        this.categoryName = !Objects.isNull(category)?category.getName():"";
        GoodsTagEntity tag = goodsTagEntityMap.get(g.getTagId());
        this.tagName = !Objects.isNull(tag)?tag.getName():"";
        this.physicalOrVirtual = g.getIsReal()==1?"实物":g.getIsReal()==0?"虚拟":"未知";
        this.marketPrice = g.getMarketPrice();
        this.purchasePrice = g.getPurchasePrice();
        this.supplier = g.getSupplierId().toString();

        int status = g.getStatus();
        this.status=status;
        this.statusStr = status==1?"已上架":status==2?"已下架":"";

        int checkStatus = g.getCheckStatus().intValue();
        this.checkStatus = checkStatus;
        this.checkStatusStr = checkStatus==0?"未审核":checkStatus==1?"审核通过":checkStatus==2?"审核不通过":"";

        UserEntity toExamineEntity = userEntityHashMap.get(Integer.parseInt(g.getCheckBy().toString()));
        this.toExamine = !Objects.isNull(toExamineEntity)?toExamineEntity.getNick():"";
        this.toExamineTime = timeStamp2Date(g.getCheckTime(),"yyyy年MM月dd日 HH:mm");

        UserEntity founderEntity = userEntityHashMap.get(Integer.parseInt(g.getCreateBy().toString()));
        this.founder = !Objects.isNull(founderEntity)?founderEntity.getNick():"";
        this.createTime = timeStamp2Date(g.getTimeCreate(),"yyyy年MM月dd日 HH:mm");

        UserEntity modifierEntity = userEntityHashMap.get(Integer.parseInt(g.getUpdateBy().toString()));
        this.modifier = !Objects.isNull(modifierEntity)?modifierEntity.getNick():"";
        this.modificationTime = date2Format(g.getUpdateTime(),"yyyy年MM月dd日 HH:mm");
    }
}

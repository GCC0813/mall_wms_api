package com.mall.wms.vo;

import com.mall.wms.entity.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.mall.wms.comm.GlobalVar.STATIC_RESOURCES_PREFIX;
import static com.mall.wms.util.DateUtil.*;

@Data
public class GoodsDetailsOut implements Serializable {

    private Long id;

    private String name;

    private List<String> picUrls;

    private List<String> detailPicUrls;

    private String synopsis;

    private String categoryName;

    private String tagName;

    private String physicalOrVirtual;

    private BigDecimal marketPrice=BigDecimal.valueOf(0.00);

    private BigDecimal purchasePrice=BigDecimal.valueOf(0.00);

    private String supplier="";

    private Integer status;

    private String statusStr="";

    private Integer checkStatus;

    private String checkStatusStr="";

    private String toExamine="";

    private String toExamineTime="";

    private String founder="";

    private String createTime="";

    private String modifier="";

    private String modificationTime="";

    public GoodsDetailsOut() {
    }

    public GoodsDetailsOut(GoodsEntity g, GoodsTagEntity goodsTagEntity,
                           GoodsCategoryEntity goodsCategoryEntity,
                           Map<Integer, UserEntity> userEntityHashMap,
                           GoodsSupplierEntity goodsSupplierEntity) {
        this.id = g.getId();
        this.name = g.getName();
        this.picUrls = (Arrays.asList(g.getPicUrls().split(","))).stream().map(p->String.format(STATIC_RESOURCES_PREFIX,p)).collect(Collectors.toList());
        List<String> detailPics = Arrays.asList(g.getDetailPicUrls().split(","));
        detailPics.forEach(dp-> dp = String.format(STATIC_RESOURCES_PREFIX,dp));
        this.detailPicUrls = detailPics;
        this.synopsis = g.getSynopsis();
        this.categoryName = !Objects.isNull(goodsCategoryEntity)&& StringUtils.isNotBlank(goodsCategoryEntity.getName())?goodsCategoryEntity.getName():"";
        this.tagName = !Objects.isNull(goodsTagEntity)&&StringUtils.isNotBlank(goodsTagEntity.getName())?goodsTagEntity.getName():"";
        this.physicalOrVirtual = g.getIsReal()==1?"实物":g.getIsReal()==0?"虚拟":"未知";
        this.marketPrice = g.getMarketPrice();
        this.purchasePrice = g.getPurchasePrice();
        this.supplier = !Objects.isNull(goodsSupplierEntity)&& StringUtils.isNotBlank(goodsSupplierEntity.getName())?goodsSupplierEntity.getName():"";

        int status = g.getStatus();
        this.status=status;
        this.statusStr = status==1?"已上架":status==2?"已下架":"";

        int checkStatus = g.getCheckStatus().intValue();
        this.checkStatus = checkStatus;
        this.checkStatusStr = checkStatus==0?"未审核":checkStatus==1?"审核通过":checkStatus==2?"审核不通过":"";

        if(!CollectionUtils.isEmpty(userEntityHashMap)){
            UserEntity toExamineEntity = userEntityHashMap.get(g.getCheckBy().intValue());
            this.toExamine = !Objects.isNull(toExamineEntity)?toExamineEntity.getNick():"";
            this.toExamineTime = timeStamp2Date(g.getCheckTime(),"yyyy年MM月dd日 HH:mm");

            UserEntity founderEntity = userEntityHashMap.get(g.getCreateBy().intValue());
            this.founder = !Objects.isNull(founderEntity)?founderEntity.getNick():"";
            this.createTime = timeStamp2Date(g.getTimeCreate(),"yyyy年MM月dd日 HH:mm");

            UserEntity modifierEntity = userEntityHashMap.get(g.getUpdateBy().intValue());
            this.modifier = !Objects.isNull(modifierEntity)?modifierEntity.getNick():"";
            this.modificationTime = date2Format(g.getUpdateTime(),"yyyy年MM月dd日 HH:mm");
        }
    }
}

package com.mall.wms.vo;

import com.mall.wms.entity.GoodsCategoryEntity;
import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.entity.GoodsTagEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.mall.wms.comm.GlobalVar.STATIC_RESOURCES_PREFIX;
import static com.mall.wms.util.DateUtil.timeStamp2Date;

@Data
public class GoodsListOut {

    private Long goodsCount;

    private List<GoodsOut> goodsOuts;

    @Data
    public static class GoodsOut{
        /**
         * 主键id
         */
        private Long id;

        /**
         * 商品名称
         */
        private String name;

        /**
         * 商品主图url列表 用逗号分隔
         */
        private String picUrls;

        /**
         * 商品标签
         */
        private String tagName;

        /**
         * 商品类别
         */
        private String categoryName;

        /**
         * 市场参考价
         */
        private BigDecimal marketPrice;


        /**
         * 商品状态 1:上架中 2:下架中
         */
        private Integer status;

        /**
         * 商品状态文案 1:上架中 2:下架中
         */
        private String statusStr;


        /**
         * 审核状态 0:未审核 1:审核通过 2:审核不通过
         */
        private Integer checkStatus;

        /**
         * 审核状态文案  0:未审核 1:审核通过 2:审核不通过
         */
        private String checkStatusStr;

        /**
         * 创建时间
         */
        private String createTime;

        public GoodsOut() {
        }

        public GoodsOut(GoodsEntity e, Map<Integer, GoodsCategoryEntity> goodsCategoryEntityMap,Map<Integer, GoodsTagEntity> goodsTagEntityMap) {
            this.id = e.getId();
            this.name = e.getName();
            List<String> pics = Arrays.asList(e.getPicUrls().split(","));
            this.picUrls = String.format(STATIC_RESOURCES_PREFIX,pics.get(0));
            this.tagName = goodsTagEntityMap.get(e.getTagId()).getName();
            this.categoryName = goodsCategoryEntityMap.get(e.getCategoryId()).getName();
            this.marketPrice = e.getMarketPrice();
            this.status = e.getStatus().intValue();
            this.statusStr = status==1?"上架中":status==2?"下架中":"";
            this.checkStatus = e.getCheckStatus().intValue();
            this.checkStatusStr = checkStatus==0?"未审核":checkStatus==1?"审核通过":checkStatus==2?"审核不通过":"";
            this.createTime = timeStamp2Date(e.getTimeCreate(),"yyyy年MM月dd日 HH:mm");
        }
    }

    public GoodsListOut() {
    }

    public GoodsListOut(Long goodsCount, List<GoodsOut> goodsOuts) {
        this.goodsCount = goodsCount;
        this.goodsOuts = goodsOuts;
    }
}

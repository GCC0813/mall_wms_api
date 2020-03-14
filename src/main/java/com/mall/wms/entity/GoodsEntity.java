package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 * 商品表
 */
@Data
public class GoodsEntity{
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
     * 商品详情图url列表 用逗号分隔
     */
    private String detailPicUrls;

    /**
     * 商品简介
     */
    private String synopsis;

    /**
     * 商品标签id
     */
    private Integer tagId;

    /**
     * 商品类别id
     */
    private Integer categoryId;

    /**
     * 商品权重
     */
    private Integer weight;

    /**
     * 是否为实物 0:虚拟 1:实物
     */
    private Byte isReal;

    /**
     * 市场参考价
     */
    private BigDecimal marketPrice;

    /**
     * 采购价
     */
    private BigDecimal purchasePrice;

    /**
     * 消耗积分
     */
    private Integer point;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 商品状态 1:上架中 2:下架中
     */
    private Byte status;

    /**
     * 删除状态 0:未删除 1:已删除
     */
    private Byte deleteFlag;

    /**
     * 审核状态 0:未审核 1:审核通过 2:审核不通过
     */
    private Byte checkStatus;

    /**
     * 审核人id
     */
    private Long checkBy;

    /**
     * 审核时间
     */
    private Integer checkTime;

    /**
     * 创建人id
     */
    private Long createBy;

    /**
     * 修改人id
     */
    private Long updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
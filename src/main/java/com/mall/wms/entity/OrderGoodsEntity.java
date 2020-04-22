package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 
 * 订单商品明细表
 */
@Data
public class OrderGoodsEntity implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 主图url
     */
    private String mainPicUrls;

    /**
     * 商品简介
     */
    private String synopsis;

    /**
     * 商品标签id
     */
    private Integer tagId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签图片url
     */
    private String tagPicUrl;

    /**
     * 商品类别id
     */
    private Integer categoryId;

    /**
     * 商品类别名称
     */
    private String categoryName;

    /**
     * 是否为实物 0:虚拟 1:实物
     */
    private Byte isReal;

    /**
     * 商品大类code 对应字典表sys_dict.data_code
     */
    private String superCategoryCode;

    /**
     * 消耗积分
     */
    private Integer point;

    /**
     * 购买数量
     */
    private Integer count;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 是否删除 0:未删除 1:删除
     */
    private Byte deleteFlag;

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


    private static OrderGoodsEntity orderGoods = new OrderGoodsEntity();

    public static OrderGoodsEntity getOrderGoodsEntity(){
        return orderGoods;
    }



}
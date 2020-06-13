package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 * 物流表
 */
@Data
public class OrderDeliveryEntity implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 物流状态
     */
    private Byte deliveryStatus;

    /**
     * 物流类型 1:发货 2:退货
     */
    private Byte deliveryType;

    /**
     * 物流公司
     */
    private String deliveryCompany;

    /**
     * 物流运费
     */
    private BigDecimal deliveryFee;

    /**
     * 物流单号
     */
    private String deliveryNo;

    /**
     * 查询物流URL
     */
    private String deliveryUrl;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 手机号区号
     */
    private String receiverPhoneArea;

    /**
     * 收货人手机号
     */
    private String receiverPhone;

    /**
     * 国家
     */
    private String receiverCountry;

    /**
     * 省
     */
    private String receiverProvince;

    /**
     * 市
     */
    private String receiverCity;

    /**
     * 区
     */
    private String receiverDistrict;

    /**
     * 街道
     */
    private String receiverStreet;

    /**
     * 收货地址
     */
    private String receiverAddress;

    /**
     * 是否删除 0:未删除 1:删除
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
package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 用户订单表
 */
@Data
public class UserOrderEntity {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 发货物流id
     */
    private Integer deliveryId;

    /**
     * 订单种类 1:用户下单 2:升级订单 3:手动创建
     */
    private Byte orderType;

    /**
     * 订单状态 10:待确认 20:已取消 21:超时取消 22:系统取消 30:待支付 40:待发货 50:已完成 60:退货中 61:已退货
     */
    private Byte orderStatus;

    /**
     * 商品总数量
     */
    private Integer goodsCount;

    /**
     * 订单金额
     */
    private Integer orderPrice;

    /**
     * 优惠金额
     */
    private Integer discountedPrice;

    /**
     * 销售渠道 1:线上 2:用户自己购买 3:admin赠送
     */
    private Integer saleChannel;

    /**
     * 订单买家备注
     */
    private String remark;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 下单时间
     */
    private Integer orderTime;

    /**
     * 支付时间
     */
    private Integer payTime;

    /**
     * 发货时间
     */
    private Integer shipTime;

    /**
     * 收货时间
     */
    private Integer confirmTime;

    /**
     * 取消时间
     */
    private Integer cancelTime;

    /**
     * 取消备注
     */
    private String cancelRemark;

    /**
     * 取消原因 1:用户自己取消 2:超时取消 3:后台手动取消 4:库存不足 5:商品下架
     */
    private Byte cancelType;

    /**
     * 订单取消返还的金额
     */
    private Integer cancelPrice;

    /**
     * 退货数量
     */
    private Integer refundNum;

    /**
     * 退货时间
     */
    private Integer refundTime;

    /**
     * 退货备注
     */
    private String refundRemark;

    /**
     * 订单退货返还的金额
     */
    private Integer refundPoint;

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
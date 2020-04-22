package com.mall.wms.vo;

import com.mall.wms.entity.OrderDeliveryEntity;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.entity.UserOrderEntity;
import com.mall.wms.util.CommUtil;
import com.mall.wms.util.DateUtil;
import lombok.Data;

import java.util.Objects;

/**
 * @author haonan
 * create on 2020/4/22 19:57
 */
@Data
public class OrderDetailsOut {

    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单状态 订单状态 1:已下单 2:已取消 3:超时取消 4:系统取消 5:待支付 6:待发货，7:已发货  8:已完成
     */
    private Integer orderStatus;


    /**
     * 发货的物流单号
     */
    private String deliveryNo;



    private String deliveryName;

    /**
     * 订单种类 1:用户下单 2:升级订单 3:手动创建
     */
    private Integer orderType;

    /**
     * 订单种类文案
     */
    private String orderTypeStr;

    /**
     * 订单状态文案
     */
    private String orderStatusStr;

    /**
     * 订单商品数量
     */
    private Integer goodsCount;

    /**
     * 订单价格
     */
    private Float orderPrice;

    /**
     * 订单备注
     */
    private String remark;


    /**
     * 购买人id
     */
    private Long payUserId;


    /**
     * 购买人姓名
     */
    private String payUserName;

    /**
     * 下单时间
     */
    private String orderTime;

    /**
     * 支付时间
     */
    private String payTime;

    /**
     * 发货时间
     */
    private String shipTime;

    /**
     * 收货时间
     */
    private String confirmTime;

    /***********************************
     * 取消时间 当状态是取消状态才有以下字段
     */
    private String cancelTime;

    /**
     * 取消备注
     */
    private String cancelRemark;

    /**
     * 取消原因 1:用户自己取消 2:超时取消 3:后台手动取消 4:库存不足 5:商品下架
     */
    private Integer cancelType;


    private String cancelTypeStr;

    /**
     * 审核状态
     */
    private Integer checkStatus;

    private String checkStatusStr;

    public OrderDetailsOut() {
    }

    public OrderDetailsOut(UserOrderEntity uo, UserEntity us, OrderDeliveryEntity od) {
        this.orderId = uo.getId();
        this.orderNo = uo.getOrderNo();
        this.orderStatus = uo.getOrderStatus().intValue();
        if(Objects.nonNull(od)){
            this.deliveryNo = od.getDeliveryNo();
            this.deliveryName = od.getDeliveryCompany();
        }
        this.orderType = uo.getOrderType().intValue();
        this.orderTypeStr = CommUtil.getOrderType(orderType);
        this.orderStatusStr = CommUtil.getOrderStatus(orderStatus);;
        this.goodsCount = uo.getGoodsCount();
        this.orderPrice = uo.getOrderPrice().floatValue();
        this.remark = uo.getRemark().replaceAll(" ","");
        if(Objects.nonNull(us)){
            this.payUserId = us.getId().longValue();
            this.payUserName = us.getNick();
        }
        String format = "yyyy年MM月dd日 HH:mm:ss";
        this.orderTime = DateUtil.timeStamp2Date(uo.getOrderTime().longValue(),format);
        this.payTime = DateUtil.timeStamp2Date(uo.getPayTime().longValue(),format);
        this.shipTime = DateUtil.timeStamp2Date(uo.getShipTime().longValue(),format);
        this.confirmTime = DateUtil.timeStamp2Date(uo.getConfirmTime().longValue(),format);
        this.cancelTime = DateUtil.timeStamp2Date(uo.getCancelTime().longValue(),format);
        this.cancelRemark = uo.getCancelRemark();
        this.cancelType = uo.getCancelType().intValue();
        this.cancelTypeStr = CommUtil.getCancelType(cancelType);
        this.checkStatus = uo.getCheckStatus().intValue();
        this.checkStatusStr = checkStatus==0?"未审核":checkStatus==1?"已审核":checkStatus==2?"未过审":"";;
    }
}

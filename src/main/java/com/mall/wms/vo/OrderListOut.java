package com.mall.wms.vo;

import lombok.Data;

import java.util.List;

/**
 * @author GCC
 * create on 2020/4/2 18:38
 */
@Data
public class OrderListOut {

    private List<Order> orders;

    @Data
    public static class Order{

        private Long orderId;

        private String orderNo;

        private Long userId;

        private String userNick;

        private String goodsName;

        private Double orderPrice;
        /**
         * 物流单号
         */
        private String deliveryNo;

        private String orderDate;
        /**
         * 订单状态 1:已下单 2:已取消 3:超时取消 4:系统取消 5:待支付 6:待发货，7:已发货  8:已完成
         */
        private Integer orderStatus;
    }
}

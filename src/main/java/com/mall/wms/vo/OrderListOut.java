package com.mall.wms.vo;

import com.mall.wms.entity.OrderDeliveryEntity;
import com.mall.wms.entity.OrderGoodsEntity;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.entity.UserOrderEntity;
import com.mall.wms.util.CommUtil;
import com.mall.wms.util.DateUtil;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

        private String deliveryName;

        private String orderDate;
        /**
         * 订单状态 1:已下单 2:已取消 3:超时取消 4:系统取消 5:待支付 6:待发货，7:已发货  8:已完成
         */
        private Integer orderStatus;

        private String orderStatusStr;



        public Order(Map<Integer, UserEntity> usersMap,
                     Map<Long, OrderGoodsEntity> orderGoodsMap,
                     Map<Long, OrderDeliveryEntity> orderDeliveryMap,
                     UserOrderEntity uo) {
            this.orderId = uo.getId();
            this.orderNo = uo.getOrderNo();
            Integer userId = uo.getUserId();
            this.userId = (long)userId;
            UserEntity userEntity = usersMap.get(userId);
            this.userNick = Objects.nonNull(userEntity)?userEntity.getNick():"";
            OrderGoodsEntity orderGoodsEntity = orderGoodsMap.get(this.orderId);
            this.goodsName = Objects.nonNull(orderGoodsEntity)?orderGoodsEntity.getName():"";
            this.orderPrice = (double)uo.getOrderPrice();
            this.deliveryNo = uo.getDeliveryNo();
            OrderDeliveryEntity orderDeliveryEntity = orderDeliveryMap.get(this.orderId);
            this.deliveryName = Objects.nonNull(orderDeliveryEntity)?orderDeliveryEntity.getDeliveryCompany():"";
            this.orderDate = DateUtil.date2Format(uo.getCreateTime(),"yyyy年MM月dd日 HH:mm");
            this.orderStatus = uo.getOrderStatus().intValue();
            this.orderStatusStr = CommUtil.getOrderStatus(uo.getOrderStatus().intValue());
        }
    }

    public OrderListOut() {
    }

    public OrderListOut(List<Order> orders) {
        this.orders = orders;
    }
}

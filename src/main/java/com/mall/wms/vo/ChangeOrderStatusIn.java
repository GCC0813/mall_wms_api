package com.mall.wms.vo;

import lombok.Data;

/**
 * @author GCC
 * create on 2020/3/26 14:48
 */
@Data
public class ChangeOrderStatusIn {

    private String orderNo;

    //订单状态 10:已下单 20:已取消 21:超时取消 22:系统取消 30:待支付 40:待发货，41:已发货  50:已完成 60:退货中 61:已退货
    private Integer status;

}

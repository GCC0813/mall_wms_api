package com.mall.wms.vo;

import lombok.Data;

/**
 * @author haonan
 * create on 2020/4/9 18:02
 */
@Data
public class OrderToDeliverIn {

    private Long orderId;

    private String deliveryNo;

    private String deliveryName;

}

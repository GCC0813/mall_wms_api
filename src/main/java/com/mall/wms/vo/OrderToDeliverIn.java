package com.mall.wms.vo;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author haonan
 * create on 2020/4/9 18:02
 */
@Data
public class OrderToDeliverIn {

    @NotNull(message = "orderId不能为空")
    private Long orderId;

    @NotBlank(message = "deliveryNo不能为空")
    private String deliveryNo;

    @NotBlank(message = "deliveryName不能为空")
    private String deliveryName;

}

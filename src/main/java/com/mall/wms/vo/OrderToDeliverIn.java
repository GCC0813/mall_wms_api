package com.mall.wms.vo;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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

    @NotBlank(message = "deliveryCompany不能为空")
    private String deliveryCompany;

    @NotNull(message = "运费不能为空")
    private BigDecimal deliveryFee;

}

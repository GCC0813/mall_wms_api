package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author haonan
 * create on 2020/4/22 19:55
 */
@Data
public class OrderDetailsIn {

    @NotBlank(message = "orderId不能为空！")
    private Long orderId;

}

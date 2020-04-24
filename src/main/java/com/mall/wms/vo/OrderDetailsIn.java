package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author haonan
 * create on 2020/4/22 19:55
 */
@Data
public class OrderDetailsIn {

    @NotNull(message = "orderId不能为空！")
    private Long orderId;

}

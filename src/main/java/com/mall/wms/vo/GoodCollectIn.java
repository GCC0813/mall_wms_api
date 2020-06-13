package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author: haonan
 * @date:2020/3/30 14:50
 */
@Data
public class GoodCollectIn {
    @NotNull(message = "用户id不能为空")
    @Min(1)
    private Integer userId;

    @NotNull(message = "商品id不能为空")
    @Min(1)
    private Integer goodsId;
}

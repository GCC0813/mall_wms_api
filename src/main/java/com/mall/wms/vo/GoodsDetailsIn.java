package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author GCC
 * create on 2020/3/12 19:42
 */
@Data
public class GoodsDetailsIn {

    @NotNull(message = "商品id不能为空")
    private Long goodsId;
}

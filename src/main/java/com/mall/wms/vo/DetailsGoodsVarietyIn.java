package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author GCC
 * create on 2020/4/9 18:55
 */
@Data
public class DetailsGoodsVarietyIn {

    @NotNull(message = "goodsId不能为空！")
    private Long goodsId;

}

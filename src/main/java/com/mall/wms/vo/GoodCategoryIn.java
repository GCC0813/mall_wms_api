package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author: haonan
 * @date:2020/3/29 14:06
 */
@Data
public class GoodCategoryIn {

    @NotNull(message = "商品种类不能为空")
    @Min(1)
    private Integer varietyId;

}

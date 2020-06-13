package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author: haonan
 * @date:2020/3/29 14:04
 */
@Data
public class GoodTagIn {

    @NotNull(message = "标签id不能为空")
    @Min(1)
    private Integer tagId;
}

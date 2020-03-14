package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author GCC
 * create on 2020/3/12 17:02
 */
@Data
public class GoodsToExamineIn {

    @NotNull(message = "goodsId不能为空！")
    private Integer goodsId;

    /**
     * 1:待审核，2:已审核，3:上架中，4：下架中
     */
    private Integer type;

}

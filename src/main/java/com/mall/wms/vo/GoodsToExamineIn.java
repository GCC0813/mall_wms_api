package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author haonan
 * create on 2020/3/12 17:02
 */
@Data
public class GoodsToExamineIn {

    @NotNull(message = "goodsId不能为空！")
    private Long goodsId;

    /**
     * 1:上架或者下架，2:审核状态
     */
    @NotNull(message = "type不能为空！")
    private Integer type;

    @NotNull(message = "status不能为空！")
    private Integer status;

    private Long userId;

    private Long time = System.currentTimeMillis()/1000;

}

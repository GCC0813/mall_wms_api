package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: cjn
 * @date:2020/3/28 17:53
 */
@Data
public class GoodNameIn {

    @NotNull
    private String goodName;

}

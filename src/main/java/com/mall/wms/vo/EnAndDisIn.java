package com.mall.wms.vo;

import lombok.Data;

@Data
public class EnAndDisIn {

    private Integer userId;

    //1:启用，//0：禁用
    private Integer type;
}

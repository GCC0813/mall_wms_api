package com.mall.wms.vo;

import lombok.Data;

@Data
public class UserOperationIn {

    private Integer userId;

    //1:启用，//0：禁用 //3：删除
    private Integer type;
}

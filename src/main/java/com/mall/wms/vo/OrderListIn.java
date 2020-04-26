package com.mall.wms.vo;

import lombok.Data;

@Data
public class OrderListIn {

    private Integer page = 1;

    private Integer limit = 10;
}

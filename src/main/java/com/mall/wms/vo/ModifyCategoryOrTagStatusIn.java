package com.mall.wms.vo;

import lombok.Data;

/**
 * @author GCC
 * create on 2020/4/14 13:43
 */
@Data
public class ModifyCategoryOrTagStatusIn {

    private Integer id;

    private Integer type;

    private Integer status;

    private Integer statusType;
}

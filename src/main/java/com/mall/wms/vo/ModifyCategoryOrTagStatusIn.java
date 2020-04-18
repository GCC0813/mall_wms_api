package com.mall.wms.vo;

import lombok.Data;

/**
 * @author GCC
 * create on 2020/4/14 13:43
 */
@Data
public class ModifyCategoryOrTagStatusIn {

    //cateid或者tagid
    private Integer id;

    //1：类型，2标签
    private Integer type;

    //所要更改得状态
    private Integer status;
    //1.启用停用，2.审核 3.删除
    private Integer statusType;
}

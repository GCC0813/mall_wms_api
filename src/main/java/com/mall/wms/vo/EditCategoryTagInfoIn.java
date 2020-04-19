package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditCategoryTagInfoIn {

    //cateid或者tagid
    @NotNull(message = "ID不能为空")
    private Integer id;

    //1：类型，2标签
    @NotNull(message = "type不能为空")
    private Integer type;

    private String name;

    private String remark;
}

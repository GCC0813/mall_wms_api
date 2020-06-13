package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddCateIn {

    @NotBlank(message = "cateName不能为空！")
    private String cateName;

    @NotBlank(message = "cateRemark不能为空！")
    private String cateRemark;

    private Integer checkStatus =0;

    private Integer status =0;
}

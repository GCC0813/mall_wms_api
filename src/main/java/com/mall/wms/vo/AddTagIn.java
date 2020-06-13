package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddTagIn {

    @NotNull(message ="cateId不能为空" )
    private Integer cateId;

    private String tagName;

    private String tagRemark;
}

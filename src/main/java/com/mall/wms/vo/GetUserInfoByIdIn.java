package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetUserInfoByIdIn {

    @NotNull(message = "id不能为空")
    private Integer id;

}

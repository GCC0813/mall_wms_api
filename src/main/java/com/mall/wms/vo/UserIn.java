package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author: cjn
 * @date:2020/3/30 15:47
 */
@Data
public class UserIn {

    @NotNull(message = "用户id不能为空")
    @Min(1)
    private Integer userId;

}

package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginIn {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String ip;

    /**
     * 0:管理员 1:商城用户
     */
    private Integer role;
}

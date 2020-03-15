package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: cjn
 * @date:2020/3/15 14:38
 */
@Data
public class UserResiterIn {

    @NotNull(message = "手机号不能为空")
    private String moblie;
    @NotNull(message = "邮箱不能为空")
    private String email;
    @NotNull(message = "密码不能为空")
    private String passWord;

    private int role;

    private int reg_time = (int) System.currentTimeMillis()/1000;
}

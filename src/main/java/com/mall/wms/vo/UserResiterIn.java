package com.mall.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

import static com.mall.wms.comm.GlobalVar.DEFAULT_HEAD;

/**
 * @author: haonan
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

    private Long reg_time = System.currentTimeMillis();

    private String headIcon = DEFAULT_HEAD;
}

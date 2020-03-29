package com.mall.wms.comm;

import lombok.Getter;

/**
 * @author GCC
 * create on 2020/1/8 20:00
 */
@Getter
public enum  CodeMsg {

    CODE_200(200, "成功"),

    SYSTEM_ERROR(500,"抱歉 系统开小差了！"),

    CODE_201(201,"该用户不存在！"),

    CODE_202(202,"用户信息修改失败！"),

    CODE_203(203,"用户注册失败！"),

    CODE_204(204,"获取用户信息失败！"),
    CODE_205(205,"用户密码有误！"),

    CODE_206(206,"请输入正确的手机号码!"),
    CODE_207(207,"请输入正确的邮箱!"),

    CODE_208(208,"更新用户状态失败"),

    CODE_209(209,"商品入库失败！"),

    CODE_210(210,"商品状态更新失败！"),

    CODE_211(211,"商品信息修改失败！"),

    CODE_212(212,"获取用户信息失败，请重新登陆！"),

    CODE_301(301,"手机已注册"),
    CODE_302(302,"邮箱已注册"),

    CODE_303(303, "删除失败"),

    CODE_304(304,"该商品不存在！"),
    CODE_307(307,"商品信息不存在！"),

    CODE_305(305,"增加用户失败！"),

    CODE_306(306,"该用户已存在！请确认手机号或邮箱"),

    ;
    private int code;

    private String msg;

    CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

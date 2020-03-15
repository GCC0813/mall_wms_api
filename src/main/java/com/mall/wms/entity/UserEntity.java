package com.mall.wms.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author 
 * 用户表
 */
@Data
public class UserEntity implements Serializable {
    private Integer id;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 用户名称
     */
    private String nick;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别 ：0男 1女 2 无性别
     */
    private Integer sex;

    /**
     * 生日
     */
    private Integer birth;

    /**
     * 用户类型 0:admin,1:商城用户，2：其他
     */
    private Integer role;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 用户头像图片地址
     */
    private String headIcon;

    /**
     * 状态位 0:未启用，1:启用
     */
    private Integer status;

    /**
     * 签名
     */
    private String tag;

    /**
     * 注册时间戳
     */
    private Integer regTime;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
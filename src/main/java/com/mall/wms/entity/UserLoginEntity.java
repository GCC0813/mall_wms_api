package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 登录表
 */
@Data
public class UserLoginEntity {
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * ip
     */
    private String ip;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public UserLoginEntity() {
    }

    public UserLoginEntity(Integer userId, String ip) {
        this.userId = userId;
        this.ip = ip;
    }
}
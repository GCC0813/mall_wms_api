package com.mall.wms.entity;

import java.io.Serializable;

/**
 * @author 
 * 阿里验证码
 */
public class BasSendSmsEntity implements Serializable {
    private Integer id;

    private String phone;

    /**
     * 国家区号
     */
    private String countrycode;

    /**
     * 是否使用 0:没有 1:有
     */
    private Byte isUse;

    private Integer code;

    /**
     * 发送验证码的类型 10.PGC注册
     */
    private Byte type;

    private Integer timeCreated;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 发送消息
     */
    private Byte isSend;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public Byte getIsUse() {
        return isUse;
    }

    public void setIsUse(Byte isUse) {
        this.isUse = isUse;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Integer timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Byte getIsSend() {
        return isSend;
    }

    public void setIsSend(Byte isSend) {
        this.isSend = isSend;
    }
}
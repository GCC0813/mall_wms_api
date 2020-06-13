package com.mall.wms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 物流公司表
 */
public class LogisticsCompanyEntity implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 物流公司名称
     */
    private String name;

    /**
     * 物流公司电话
     */
    private String phone;

    /**
     * 物流公司图标
     */
    private String iconUrl;

    /**
     * 查询物流的网页地址
     */
    private String queryUrl;

    /**
     * 1:启用,0:停用
     */
    private Integer status;

    /**
     * 公司地址
     */
    private String companyAddr;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
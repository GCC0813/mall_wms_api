package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 物流公司表
 */
@Data
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

}
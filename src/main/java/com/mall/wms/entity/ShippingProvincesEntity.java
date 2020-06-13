package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 
 * 省份表
 */
@Data
public class ShippingProvincesEntity implements Serializable {
    private Integer id;

    /**
     * 省份
     */
    private String name;

    /**
     * 父节点id
     */
    private Integer pid;

}
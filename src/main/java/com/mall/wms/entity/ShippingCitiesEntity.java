package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 
 * 城市字典表
 */
@Data
public class ShippingCitiesEntity implements Serializable {
    private Integer id;

    /**
     * 城市
     */
    private String name;

    /**
     * 省份id，province.id
     */
    private Integer pid;

}
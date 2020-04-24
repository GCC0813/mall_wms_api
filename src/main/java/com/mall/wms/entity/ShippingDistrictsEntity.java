package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 
 * 区字典
 */
@Data
public class ShippingDistrictsEntity implements Serializable {
    private Integer id;

    /**
     * 地区名
     */
    private String name;

    /**
     * 父节点id
     */
    private Integer pid;

}
package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 管理列表
 */
@Data
public class ManagementListEntity implements Serializable {
    private Integer id;

    /**
     * 管理名称
     */
    private String name;

    /**
     * 上一层id
     */
    private Integer lastId;

    /**
     * 是否启用
     */
    private Integer isEnable;

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
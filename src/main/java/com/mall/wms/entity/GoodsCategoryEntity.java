package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 商品类别表
 */
@Data
public class GoodsCategoryEntity implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别备注
     */
    private String remark;

    /**
     * 删除状态 0:未删除 1:已删除
     */
    private Byte deleteFlag;

    private Integer status;

    /**
     * 审核状态 0:未审核 1:审核通过 2:审核不通过
     */
    private Byte checkStatus;

    /**
     * 审核人id
     */
    private Long checkBy;

    /**
     * 审核时间
     */
    private Integer checkTime;

    /**
     * 创建人id
     */
    private Long createBy;

    /**
     * 修改人id
     */
    private Long updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

}
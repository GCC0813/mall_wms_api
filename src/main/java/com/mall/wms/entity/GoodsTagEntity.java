package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 商品标签表
 */
@Data
public class GoodsTagEntity implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签图片url
     */
    private String picUrl;

    /**
     * 标签备注
     */
    private String remark;

    /**
     * 删除状态 0:未删除 1:已删除
     */
    private Byte deleteFlag;

    /**
     * 状态
     */
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
     * 类别id
     */
    private Integer categoryId;

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
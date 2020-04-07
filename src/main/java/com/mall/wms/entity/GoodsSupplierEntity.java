package com.mall.wms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 积分商城-供应商
 */
@Data
public class GoodsSupplierEntity implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 供应商联系人
     */
    private String concatName;

    /**
     * 供应商联系电话
     */
    private String concatPhone;

    /**
     * 供应商联系地址
     */
    private String concatAddress;

    /**
     * 供应商银行名称
     */
    private String bankName;

    /**
     * 供应商银行卡号
     */
    private String bankCardNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除状态 0:未删除 1:已删除
     */
    private Byte deleteFlag;

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
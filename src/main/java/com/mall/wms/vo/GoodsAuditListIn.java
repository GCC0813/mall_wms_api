package com.mall.wms.vo;

import lombok.Data;

/**
 * @author haonan
 * create on 2020/3/12 17:10
 */
@Data
public class GoodsAuditListIn {

    //0:全部，1:待审核，2:已审核，3:上架中，4：下架中
    private Integer type;

    private Long startTime;

    private Long endTime;
    //模糊搜索
    private String vague;

    private Integer categoryId;

    private Integer tagId;

    private Integer currentPage;

    private Integer pageSize;
}

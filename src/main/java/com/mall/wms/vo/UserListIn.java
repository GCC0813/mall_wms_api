package com.mall.wms.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class UserListIn {

    private Long startTime;

    private Long endTime;

    private String userName;

    private String email;

    private String phone;

    private Integer page=1;

    private Integer pageSize=8;

}

package com.mall.wms.vo;

import lombok.Data;
import sun.dc.pr.PRError;

import java.util.List;

/**
 * @author: cjn
 * @date:2020/3/29 12:52
 */
@Data
public class GoodVrietyOut {

    private Integer vrietyId;

    private String vrietyName;

    private List<GoodTagsOut> goodTagsList;
}

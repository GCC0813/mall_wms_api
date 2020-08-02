package com.mall.wms.vo;

import com.mall.wms.enums.GoodsEnums;
import lombok.Data;

import java.util.List;

/**
 * @author: haonan
 * @date:2020/3/29 12:52
 */
@Data
public class GoodVrietyOut {

    private Integer vrietyId;

    private String vrietyName;

    private List<GoodTagsOut> goodTagsList;

    public GoodVrietyOut() {
    }

    public GoodVrietyOut(GoodsEnums e, List<GoodTagsOut> goodTagsList) {
        this.vrietyId = e.getId();
        this.vrietyName = e.getName();
        this.goodTagsList = goodTagsList;
    }

    public GoodVrietyOut(GoodVrietyOut out, List<GoodTagsOut> goodTagsList) {
        this.vrietyId = out.getVrietyId();
        this.vrietyName = out.getVrietyName();
        this.goodTagsList = goodTagsList;
    }
}

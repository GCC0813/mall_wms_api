package com.mall.wms.vo;

import com.mall.wms.entity.GoodsTagEntity;
import com.mall.wms.enums.GoodsEnums;
import lombok.Data;

/**
 * @author: cjn
 * @date:2020/3/29 12:56
 */
@Data
public class GoodTagsOut {

    private Integer tagId;

    private String tagName;

    public GoodTagsOut() {
    }

    public GoodTagsOut(GoodsEnums e) {
        this.tagId = e.getId();
        this.tagName = e.getName();
    }

    public GoodTagsOut(GoodTagsOut out) {
        this.tagId = out.getTagId();
        this.tagName = out.getTagName();
    }

    public GoodTagsOut(GoodsTagEntity goodsTagEntity) {
        this.tagId = goodsTagEntity.getId();
        this.tagName = goodsTagEntity.getName();
    }
}

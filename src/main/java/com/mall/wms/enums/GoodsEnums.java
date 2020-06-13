package com.mall.wms.enums;

import lombok.Getter;

@Getter
public enum GoodsEnums {
    STATIC_CATEGORY(0,"请选择商品类别"),
    STATIC_TAGS(0,"请选择商品标签"),
    ;





    private Integer id;
    private String name;

    GoodsEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

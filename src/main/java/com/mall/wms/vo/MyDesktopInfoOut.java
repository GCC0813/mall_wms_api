package com.mall.wms.vo;

import lombok.Data;

@Data
public class MyDesktopInfoOut {

    private String name;

    private long number;

    public MyDesktopInfoOut() {
    }

    public MyDesktopInfoOut(String name, long number) {
        this.name = name;
        this.number = number;
    }
}

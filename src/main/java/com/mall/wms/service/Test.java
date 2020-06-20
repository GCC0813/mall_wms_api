package com.mall.wms.service;

import com.mall.wms.entity.GoodsCategoryEntity;

import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author haonan
 * create on 2020/4/13 14:02
 */
public class Test {


    public static void main(String[] args) throws Exception {

        Class clazz = Class.forName("com.mall.wms.entity.GoodsCategoryEntity");
        Constructor constructor = clazz.getConstructor(null);
        GoodsCategoryEntity entity = (GoodsCategoryEntity)constructor.newInstance();
        entity.setStatus(1);
        System.out.println(entity);
    }

}

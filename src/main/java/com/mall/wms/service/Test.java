package com.mall.wms.service;

import com.mall.wms.entity.SingleObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author GCC
 * create on 2020/4/13 14:02
 */
public class Test {


    public static void main(String[] args) {
        /*LocalDate date  = LocalDate.now();
        for (int i=7;i>0;i--){
            String str = date.minusDays(i).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            System.out.println(str);

        }
        System.out.println(date);*/

        SingleObject object = SingleObject.getInstance();
        object.setId(1);
        object.setName("111");
        System.out.println(object);
        SingleObject object1 = SingleObject.getInstance();
        object1.setId(2);
        object1.setName("222");
        System.out.println(object1);
        System.out.println(object.equals(object1));
        object.showMessage();

    }

}

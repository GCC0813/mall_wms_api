package com.mall.wms.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author haonan
 * create on 2020/4/13 14:02
 */
public class Test {


    public static void main(String[] args) {
        LocalDate date  = LocalDate.now();
        for (int i=7;i>0;i--){
            String str = date.minusDays(i).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            System.out.println(str);

        }

        System.out.println(date);


    }

}

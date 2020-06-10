package com.mall.wms.service;

import com.mall.wms.entity.SingleObject;
import org.apache.logging.log4j.core.util.Assert;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GCC
 * create on 2020/4/13 14:02
 */
public class Test {


    public static void main(String[] args) {
        List<String> b= new ArrayList<>();
        b.add("1");
        b.add("2");
        b.add("3");
        String a = "123";
        String c= String.join(",", b);
        System.out.println(b.toString().substring(1,8));
        System.out.println(c);
    }

}

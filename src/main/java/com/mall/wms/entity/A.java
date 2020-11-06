package com.mall.wms.entity;

public class A {

    {
        System.out.println("父类");
    }

    private static int z = 1;

    public A(){
        System.out.println("z:"+(++z));
    }


}

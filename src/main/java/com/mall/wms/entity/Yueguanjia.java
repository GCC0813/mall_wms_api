package com.mall.wms.entity;

import java.util.Arrays;

public class Yueguanjia extends A {
    public static int k=0;
    public static Yueguanjia t1 = new Yueguanjia("t1");

    public static Yueguanjia t2 = new Yueguanjia("t2");
    public static int i = print("i");
    public static int n=99;
    private int a=0;
    public int j=print("j");
    {
        print("构造块");
    }
    static {
        print("静态块");
    }
    public Yueguanjia(String str){
        System.out.println((++k)+";"+str+"  i="+i+"   n:"+n);
        ++i;
        ++n;
    }
    public static int print(String str){
        System.out.println((++k)+";"+str+"  i="+i+"   n:"+n);
        ++n;
        return  ++i;
    }

    public static void main(String[] args) {
        String a = "asdsadasdsa";
        assert a.equals("ddsadsadsadsadasasd");

    }


}

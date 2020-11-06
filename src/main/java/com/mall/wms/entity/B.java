package com.mall.wms.entity;

public class B extends A {

    static {
        System.out.println("我是B的静态1！");
    }
    static {
        System.out.println("我是B的静态2！");
    }

    public B() {
        System.out.println("我是B的无参构造");
    }

    public static void main(String[] args) {
        new B();
        new B();
    }
}

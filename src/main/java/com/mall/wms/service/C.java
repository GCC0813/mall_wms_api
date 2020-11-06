package com.mall.wms.service;

public class C extends B{

    @Override
    public void a() {
        System.out.println("我是a方法！");
    }

    @Override
    public void b() {
        System.out.println("我是b方法！");
    }

    public static void main(String[] args) {
        C c = new C();
        c.a();
        c.b();
        B b = new B();
        b.a();
        b.b();

    }
}

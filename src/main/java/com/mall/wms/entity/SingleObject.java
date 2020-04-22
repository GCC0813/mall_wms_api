package com.mall.wms.entity;

import lombok.Data;

import java.util.Objects;

/**
 * @author GCC
 * create on 2020/4/21 13:46
 */
@Data
public class SingleObject {

    private Integer id;

    private String name;

    //创建 SingleObject 的一个对象
    private static SingleObject instance;

    //让构造函数为 private，这样该类就不会被实例化
    private SingleObject(){}

    //获取唯一可用的对象
    public static SingleObject getInstance(Integer id, String name){
        if(Objects.isNull(instance)){
            instance = new SingleObject(id,name);
        }
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }

    private SingleObject(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

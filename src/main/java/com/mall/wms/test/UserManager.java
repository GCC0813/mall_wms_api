package com.mall.wms.test;

/**
 * @author GCC
 * create on 2020/5/7 15:48
 */
public interface UserManager {

    //新增用户抽象方法
    void addUser(String userName,String password);
    //删除用户抽象方法
    void delUser(String userName);
    
}

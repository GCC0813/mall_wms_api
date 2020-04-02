package com.mall.wms.service;

import com.mall.wms.entity.UserEntity;
import com.mall.wms.entity.UserOrderEntity;
import com.mall.wms.mapper.UserOrderMapper;
import com.mall.wms.vo.OrderListOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GCC
 * create on 2020/4/2 18:35
 */
@Service
public class OrderService {

    @Autowired
    UserOrderMapper userOrderMapper;


    public OrderListOut orderList(){
        return null;
    }
}

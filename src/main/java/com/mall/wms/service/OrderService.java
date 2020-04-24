package com.mall.wms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.OrderDeliveryEntity;
import com.mall.wms.entity.OrderGoodsEntity;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.entity.UserOrderEntity;
import com.mall.wms.mapper.OrderDeliveryMapper;
import com.mall.wms.mapper.OrderGoodsMapper;
import com.mall.wms.mapper.UserMapper;
import com.mall.wms.mapper.UserOrderMapper;
import com.mall.wms.vo.*;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.mall.wms.comm.CodeMsg.*;

/**
 * @author haonan
 * create on 2020/4/2 18:35
 */
@Service
public class OrderService {

    @Autowired
    UserOrderMapper userOrderMapper;

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderDeliveryMapper orderDeliveryMapper;

    @Autowired
    GoodsService goodsService;

    public OrderListOut orderList() {
    public OrderListOut orderList(OrderListIn in) {
        Page page = PageHelper.startPage(in.getPage(),in.getLimit());
        List<UserOrderEntity> userOrders = userOrderMapper.selectAll();
        List<OrderListOut.Order> orders = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userOrders)) {
            List<Long> orderIds = new ArrayList<>();
            List<Long> userIds = new ArrayList<>();
            userOrders.forEach(uo -> {
                orderIds.add(uo.getId());
                userIds.add((long) uo.getUserId());
            });
            List<OrderGoodsEntity> orderGoods = orderGoodsMapper.selectByOrderIds(orderIds);
            Map<Long, OrderGoodsEntity> orderGoodsMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(orderGoods)) {
                orderGoodsMap = orderGoods.stream().collect(Collectors.toMap(OrderGoodsEntity::getOrderId, Function.identity()));
            }

            List<OrderDeliveryEntity> orderDeliveries = orderDeliveryMapper.selectOrderDeliveryByOrderIds(orderIds);
            Map<Long, OrderDeliveryEntity> orderDeliveryMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(orderDeliveries)) {
                orderDeliveryMap = orderDeliveries.stream().collect(Collectors.toMap(OrderDeliveryEntity::getOrderId, Function.identity()));
            }

            List<UserEntity> users = userMapper.selectUserListByIds(userIds, 1);
            Map<Integer, UserEntity> usersMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(orderGoods)) {
                usersMap = users.stream().collect(Collectors.toMap(UserEntity::getId, Function.identity()));
            }

            for (UserOrderEntity uo : userOrders) {
                orders.add(new OrderListOut.Order(usersMap, orderGoodsMap, orderDeliveryMap, uo));
            }
        }
        return new OrderListOut(orders){{setCount(page.getTotal());}};
    }


    public OrderDetailsOut orderDetails(OrderDetailsIn in) {
        UserOrderEntity userOrderEntity = userOrderMapper.selectByPrimaryKey(in.getOrderId());
        if(Objects.isNull(userOrderEntity)){
            throw new BizException(CODE_612);
        }
        UserEntity userEntity = userMapper.selectByPrimaryKey(userOrderEntity.getUserId());

        OrderDeliveryEntity orderDeliveryEntity = orderDeliveryMapper.selectByPrimaryKey(userOrderEntity.getDeliveryId().longValue());
        return new OrderDetailsOut(userOrderEntity,userEntity,orderDeliveryEntity);
    }


    public CodeMsg toDeliverGoods(OrderToDeliverIn in){
        OrderGoodsEntity orderGoodsEntity = OrderGoodsEntity.getOrderGoodsEntity();
        int rows = orderGoodsMapper.insertSelective(new OrderGoodsEntity());
        if(rows<1){
            throw new BizException(CODE_613);
        }
        return CODE_200;
    }


    public GoodsDetailsOut goodsDetails(OrderDetailsIn in){
        OrderGoodsEntity orderGoodsEntity =  orderGoodsMapper.selectByPrimaryKey(in.getOrderId());
        GoodsDetailsOut out = null;
        if(Objects.nonNull(orderGoodsEntity)){
            GoodsDetailsIn goodsDetailsIn = new GoodsDetailsIn();
            goodsDetailsIn.setGoodsId(orderGoodsEntity.getGoodsId());
            out = goodsService.goodsDetails(goodsDetailsIn);
        }

        if(Objects.isNull(out)){
            throw new BizException(CODE_307);
        }
        return out;
    }

    /**
     * 物流详情
     */
    public CodeMsg logisticsDetails(OrderDetailsIn in){
        return null;
    }
}

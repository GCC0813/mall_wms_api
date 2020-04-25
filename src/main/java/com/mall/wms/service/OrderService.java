package com.mall.wms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.*;
import com.mall.wms.mapper.*;
import com.mall.wms.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.mall.wms.comm.CodeMsg.*;
import static com.mall.wms.comm.exceptionhandler.BizException.bizException;
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

    @Autowired
    LogisticsCompanyMapper logisticsCompanyMapper;

    public OrderListOut orderList(OrderListIn in) {
        Page page = PageHelper.startPage(in.getPage(), in.getLimit());
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
        return new OrderListOut(orders) {{
            setCount(page.getTotal());
        }};
    }


    public OrderDetailsOut orderDetails(OrderDetailsIn in) {
        UserOrderEntity userOrderEntity = userOrderMapper.selectByPrimaryKey(in.getOrderId());
        if (Objects.isNull(userOrderEntity)) {
            throw bizException(CODE_612);
        }
        UserEntity userEntity = userMapper.selectByPrimaryKey(userOrderEntity.getUserId());

        OrderDeliveryEntity orderDeliveryEntity = orderDeliveryMapper.selectByPrimaryKey(userOrderEntity.getDeliveryId().longValue());
        return new OrderDetailsOut(userOrderEntity, userEntity, orderDeliveryEntity);
    }


    @Transactional(rollbackFor = BizException.class)
    public CodeMsg toDeliverGoods(OrderToDeliverIn in) {
        UserOrderEntity userOrderEntity =userOrderMapper.selectByPrimaryKey(in.getOrderId());
        if(Objects.isNull(userOrderEntity)){
            throw bizException(CODE_612);
        }
        UserOrderEntity userOrder = new UserOrderEntity();
        userOrder.setId(in.getOrderId());
        userOrder.setOrderStatus(Integer.valueOf(7).byteValue());
        int rows =  userOrderMapper.updateByPrimaryKeySelective(userOrder);
        if(rows<1){
            throw bizException(CODE_613);
        }
        OrderDeliveryEntity orderDeliveryEntity = new OrderDeliveryEntity();
        orderDeliveryEntity.setId(userOrderEntity.getDeliveryId().longValue());
        orderDeliveryEntity.setDeliveryNo(in.getDeliveryNo());
        orderDeliveryEntity.setDeliveryCompany(in.getDeliveryCompany());
        orderDeliveryEntity.setDeliveryFee(in.getDeliveryFee());
        orderDeliveryMapper.updateByPrimaryKeySelective(orderDeliveryEntity);
        return CODE_200;
    }


    public GoodsDetailsOut goodsDetails(OrderDetailsIn in) {
        OrderGoodsEntity orderGoodsEntity = orderGoodsMapper.selectByPrimaryKey(in.getOrderId());
        GoodsDetailsOut out = null;
        if (Objects.nonNull(orderGoodsEntity)) {
            GoodsDetailsIn goodsDetailsIn =GoodsDetailsIn.getGoodsDetailsIn();
            goodsDetailsIn.setGoodsId(orderGoodsEntity.getGoodsId());
            out = goodsService.goodsDetails(goodsDetailsIn);
        }

        if (Objects.isNull(out)) {
            throw bizException(CODE_307);
        }
        return out;
    }

    /**
     * 物流详情
     */
    public CodeMsg logisticsDetails(OrderDetailsIn in) {
        return null;
    }

    public  List<LogisticsCompanyEntity> logisticsCompany(){
        return logisticsCompanyMapper.selectAll();
    }


    public  CodeMsg  orderDelete(OrderDetailsIn in){
        UserOrderEntity userOrderEntity = new UserOrderEntity();
        userOrderEntity.setId(in.getOrderId());
        userOrderEntity.setDeleteFlag(Byte.valueOf("1"));
        int rows = userOrderMapper.updateByPrimaryKeySelective(userOrderEntity);
        if(rows<1){
            throw bizException(CODE_614);
        }
        return CODE_200;
    }


}

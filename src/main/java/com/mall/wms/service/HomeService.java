package com.mall.wms.service;

import com.mall.wms.entity.UserEntity;
import com.mall.wms.entity.UserOrderEntity;
import com.mall.wms.mapper.GoodsMapper;
import com.mall.wms.mapper.UserMapper;
import com.mall.wms.mapper.UserOrderMapper;
import com.mall.wms.vo.HomeInfoOut;
import com.mall.wms.vo.MyDesktopInfoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author GCC
 * create on 2020/3/12 22:17
 */
@Service
public class HomeService {

    @Autowired
    private CommService commService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserOrderMapper userOrderMapper;

    public HomeInfoOut getHomeInfo(){
        UserEntity user =(UserEntity) commService.isLogin();
        return new HomeInfoOut(user);
    }

    public List<MyDesktopInfoOut> myDesktopInfo(){
        List<MyDesktopInfoOut> myDesktopInfoOuts = new ArrayList<>();
        long userCount = userMapper.selectCount();
        myDesktopInfoOuts.add(new MyDesktopInfoOut("会员数",userCount));
        long goodsCount = goodsMapper.selectCount();
        myDesktopInfoOuts.add(new MyDesktopInfoOut("商品数",goodsCount));

        myDesktopInfoOuts.add(new MyDesktopInfoOut("商品种类数",goodsCount));

        List<UserOrderEntity> orders = userOrderMapper.selectAll();
        long paid =0L,shipped=0L;
        if(!CollectionUtils.isEmpty(orders)) {
            for (UserOrderEntity e : orders) {
                int status = e.getOrderStatus();
                if (status == 10) {
                    paid++;
                }
                if (status == 41) {
                    shipped++;
                }
            }
        }
        myDesktopInfoOuts.add(new MyDesktopInfoOut("订单数",orders.size()));
        myDesktopInfoOuts.add(new MyDesktopInfoOut("已付款",paid));
        myDesktopInfoOuts.add(new MyDesktopInfoOut("已发货",shipped));
        return myDesktopInfoOuts;
    }

}

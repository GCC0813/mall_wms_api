package com.mall.wms.service;

import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.entity.UserOrderEntity;
import com.mall.wms.mapper.GoodsMapper;
import com.mall.wms.mapper.UserMapper;
import com.mall.wms.mapper.UserOrderMapper;
import com.mall.wms.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.mall.wms.util.DateUtil.*;

/**
 * @author haonan
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

    public StatisticsOut getStatistics(){
        int nowMonth = getNowMonth();
        List<String> lastMonths = new ArrayList<>();
        List<Integer> lastMonthIds= new ArrayList<>();

        for(int i=6;i<=6 && i>=0;i--) {
            int month = getPreviousMonth(nowMonth,i);
            if(month==nowMonth){
                lastMonths.add("本月");
                lastMonthIds.add(month);
                continue;
            }
            lastMonths.add(getMonthText(month));
            lastMonthIds.add(month);
        }
        int lastOneMonth = lastMonthIds.get(0);
        Map<String,Long> lastOneMonthMap =  getFirstLastTimeStampByYearMonth(getLastMonthInYearByMonth(lastOneMonth,nowMonth),lastOneMonth);
        int lastTwoMonth = lastMonthIds.get(1);
        Map<String,Long> lastTwoMonthMap =  getFirstLastTimeStampByYearMonth(getLastMonthInYearByMonth(lastTwoMonth,nowMonth),lastTwoMonth);
        int lastThreeMonth = lastMonthIds.get(2);
        Map<String,Long> lastThreeMonthMap =  getFirstLastTimeStampByYearMonth(getLastMonthInYearByMonth(lastThreeMonth,nowMonth),lastThreeMonth);
        int lastFourMonth = lastMonthIds.get(3);
        Map<String,Long> lastFourMonthMap =  getFirstLastTimeStampByYearMonth(getLastMonthInYearByMonth(lastFourMonth,nowMonth),lastFourMonth);
        int lastFiveMonth = lastMonthIds.get(4);
        Map<String,Long> lastFiveMonthMap =  getFirstLastTimeStampByYearMonth(getLastMonthInYearByMonth(lastFiveMonth,nowMonth),lastFiveMonth);
        int lastSixMonth = lastMonthIds.get(5);
        Map<String,Long> lastSixMonthMap =  getFirstLastTimeStampByYearMonth(getLastMonthInYearByMonth(lastSixMonth,nowMonth),lastSixMonth);
        int lastSevenMonth = lastMonthIds.get(6);
        Map<String,Long> lastSevenMonthMap =  getFirstLastTimeStampByYearMonth(getLastMonthInYearByMonth(lastSevenMonth,nowMonth),lastSevenMonth);

        Long firstTimeInMillis = lastOneMonthMap.get("firstTimeInMillis");
        Long lastTimeInMillis = lastSevenMonthMap.get("lastTimeInMillis");

        UserListIn userListIn = new UserListIn();
        userListIn.setStartTime(firstTimeInMillis);
        userListIn.setEndTime(lastTimeInMillis);
        List<UserEntity> userEntities = userMapper.selectAllByCondition(userListIn);
        List<Long> userNum = new ArrayList<>();
        if(!CollectionUtils.isEmpty(userEntities)){
            userNum.add(getUserNumSize(userEntities,lastOneMonthMap));
            userNum.add(getUserNumSize(userEntities,lastTwoMonthMap));
            userNum.add(getUserNumSize(userEntities,lastThreeMonthMap));
            userNum.add(getUserNumSize(userEntities,lastFourMonthMap));
            userNum.add(getUserNumSize(userEntities,lastFiveMonthMap));
            userNum.add(getUserNumSize(userEntities,lastSixMonthMap));
            userNum.add(getUserNumSize(userEntities,lastSevenMonthMap));
        }

        GoodsAuditListIn goodsAuditListIn = new GoodsAuditListIn();
        goodsAuditListIn.setStartTime(firstTimeInMillis);
        goodsAuditListIn.setEndTime(lastTimeInMillis);
        List<GoodsEntity> goodsEntities = goodsMapper.selectByCondition(goodsAuditListIn);
        List<Long> goodsNum = new ArrayList<>();
        if(!CollectionUtils.isEmpty(goodsEntities)){
            goodsNum.add(getGoodsNumSize(goodsEntities,lastOneMonthMap));
            goodsNum.add(getGoodsNumSize(goodsEntities,lastTwoMonthMap));
            goodsNum.add(getGoodsNumSize(goodsEntities,lastThreeMonthMap));
            goodsNum.add(getGoodsNumSize(goodsEntities,lastFourMonthMap));
            goodsNum.add(getGoodsNumSize(goodsEntities,lastFiveMonthMap));
            goodsNum.add(getGoodsNumSize(goodsEntities,lastSixMonthMap));
            goodsNum.add(getGoodsNumSize(goodsEntities,lastSevenMonthMap));
        }

        List<UserOrderEntity> userOrderEntities = userOrderMapper.selectByDate(firstTimeInMillis,lastTimeInMillis);
        List<Long> orderNum = new ArrayList<>();
        if(!CollectionUtils.isEmpty(goodsEntities)){
            orderNum.add(getOrderNumSize(userOrderEntities,lastOneMonthMap));
            orderNum.add(getOrderNumSize(userOrderEntities,lastTwoMonthMap));
            orderNum.add(getOrderNumSize(userOrderEntities,lastThreeMonthMap));
            orderNum.add(getOrderNumSize(userOrderEntities,lastFourMonthMap));
            orderNum.add(getOrderNumSize(userOrderEntities,lastFiveMonthMap));
            orderNum.add(getOrderNumSize(userOrderEntities,lastSixMonthMap));
            orderNum.add(getOrderNumSize(userOrderEntities,lastSevenMonthMap));
        }
        return StatisticsOut.getStatisticsOut(lastMonths,userNum,goodsNum,orderNum);
    }

    private Long getUserNumSize(List<UserEntity> userEntities, Map<String,Long> map) {
        return  userEntities.stream().filter(u -> u.getRegTime() >= map.get("firstTimeInMillis") &&
                u.getRegTime() <= map.get("lastTimeInMillis")).count();
    }

    private Long getGoodsNumSize(List<GoodsEntity> goodsEntities, Map<String,Long> map){
        return  goodsEntities.stream().filter(g -> g.getTimeCreate() >= map.get("firstTimeInMillis") &&
                g.getTimeCreate() <= map.get("lastTimeInMillis")).count();
    }

    private Long getOrderNumSize(List<UserOrderEntity> userOrderEntities, Map<String,Long> map){
        return  userOrderEntities.stream().filter(u -> u.getCreateTime().getTime()/1000 >= map.get("firstTimeInMillis") &&
                u.getCreateTime().getTime()/1000 <= map.get("lastTimeInMillis")).count();
    }




    public static void main(String[] args) {
        int nowMonth = getNowMonth();
        List<String> lastMonths = new ArrayList<>();
        List<Integer> lastMonthIds= new ArrayList<>();

        for(int i=6;i<=6 && i>=0;i--) {
            int month = getPreviousMonth(nowMonth,i);
            lastMonths.add(getMonthText(month));
            lastMonthIds.add(month);
        }

    }

    private static int getLastMonthInYearByMonth(int lastMonth,int nowMonth){
        int nowYear = getNowYear();
        if(lastMonth-nowMonth > 0){
            return nowYear-1;
        }else {
            return nowYear;
        }
    }


}

package com.mall.wms.service;

import com.mall.wms.entity.UserEntity;
import com.mall.wms.entity.UserOrderEntity;
import com.mall.wms.mapper.GoodsMapper;
import com.mall.wms.mapper.UserMapper;
import com.mall.wms.mapper.UserOrderMapper;
import com.mall.wms.vo.HomeInfoOut;
import com.mall.wms.vo.MyDesktopInfoOut;
import com.mall.wms.vo.StatisticsOut;
import com.mall.wms.vo.UserListIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

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

        UserListIn in = new UserListIn();
        in.setStartTime(firstTimeInMillis);
        in.setEndTime(lastTimeInMillis);
        List<UserEntity> userEntities = userMapper.selectAllByCondition(in);
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
        return StatisticsOut.getStatisticsOut(lastMonths,userNum,new ArrayList<>(),new ArrayList<>());
    }

    private Long getUserNumSize(List<UserEntity> userEntities, Map<String,Long> map) {
        return (long) userEntities.stream().filter(u -> u.getRegTime() >= map.get("firstTimeInMillis") &&
                u.getRegTime() <= map.get("lastTimeInMillis")).collect(Collectors.toList()).size();
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

package com.mall.wms.vo;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class StatisticsOut {

    private List<String> months;

    private List<Long> userNum;

    private List<Long> goodsNum;

    private List<Long> orderNum;

    public StatisticsOut(List<String> months, List<Long> userNum, List<Long> goodsNum, List<Long> orderNum) {
        this.months = months;
        this.userNum = userNum;
        this.goodsNum = goodsNum;
        this.orderNum = orderNum;
    }

    public static StatisticsOut out;

    public static StatisticsOut getStatisticsOut(List<String> months, List<Long> userNum, List<Long> goodsNum, List<Long> orderNum){
        if(Objects.isNull(out)){
            out = new StatisticsOut(months,userNum,goodsNum,orderNum);
        }
        return out;
    }
}

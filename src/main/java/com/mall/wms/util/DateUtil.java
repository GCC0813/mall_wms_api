package com.mall.wms.util;

import org.apache.commons.lang3.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haonan
 * create on 2020/3/6 16:35
 */
public class DateUtil {

    public static String date2Format(Date date, String format){
        if(StringUtils.isBlank(format)){
            format="yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(format).format(date);
    }

    public static String timeStamp2Date(Long timeStamp,String format){
        if(StringUtils.isBlank(format)){
            format="yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(format).format(timeStamp*1000L);
    }

    /**
     * 获取指定年份月份的第一天零点时间戳和最后一天零点时间戳
     * @param year 年
     * @param month 月
     * @return map
     */
    public static Map<String,Long> getFirstLastTimeStampByYearMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        // 不加度下面2行，就是取当前版时间前一个月的第一天权及最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long lastTimeInMillis = cal.getTimeInMillis() / 1000 + 86399;
        cal.set(Calendar.DAY_OF_MONTH, 1);
        long firstTimeInMillis = cal.getTimeInMillis() / 1000;
        return new HashMap<String, Long>() {{
            put("firstTimeInMillis", firstTimeInMillis);
            put("lastTimeInMillis", lastTimeInMillis);
        }};
    }

    /**
     * 根据本月获取前面第num月份
     * @param nowMonth 当前
     * @param num 获取前面月份
     * @return int
     */
    public static int getPreviousMonth(int nowMonth,int num){
        return nowMonth-num<=0?nowMonth-num+12:nowMonth-num;
    }

    /**
     * 获取当前月份
     * @return
     */
    public static int getNowMonth(){
        Calendar cale = Calendar.getInstance();
        return cale.get(Calendar.MONTH) + 1;
    }

    public static int getNowYear(){
        Calendar cale = Calendar.getInstance();
        return cale.get(Calendar.YEAR);
    }





    public static String getMonthText(int month) {
        String str = "";
        switch (month) {
            case 1:
                str = "一月";
                break;
            case 2:
                str = "二月";
                break;
            case 3:
                str = "三月";
                break;
            case 4:
                str = "四月";
                break;
            case 5:
                str = "五月";
                break;
            case 6:
                str = "六月";
                break;
            case 7:
                str = "七月";
                break;
            case 8:
                str = "八月";
                break;
            case 9:
                str = "九月";
                break;
            case 10:
                str = "十月";
                break;
            case 11:
                str = "十一月";
                break;
            case 12:
                str = "十二月";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(timeStamp2Date(new Date().getTime()/1000,""));;
    }
}

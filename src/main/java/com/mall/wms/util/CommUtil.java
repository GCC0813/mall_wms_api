package com.mall.wms.util;

/**
 * @author haonan
 * create on 2020/4/8 19:40
 */
public class CommUtil {

    /**
     * 订单状态 1:已下单 2:已取消 3:超时取消 4:系统取消 5:待支付 6:待发货，7:已发货  8:已完成
     */
    public static String getOrderStatus(int status) {
        String str;
        switch (status) {
            case 1:
                str = "已下单";
                break;
            case 2:
                str = "已取消";
                break;
            case 3:
                str = "超时取消";
                break;
            case 4:
                str = "系统取消";
                break;
            case 5:
                str = "待支付";
                break;
            case 6:
                str = "待发货";
                break;
            case 7:
                str = "已发货";
                break;
            case 8:
                str = "已完成";
                break;
            default:
                str = "";
        }
        return str;
    }

    public static String getOrderType(int type){
        String str;
        switch (type) {
            case 1:
                str = "用户下单";
                break;
            case 2:
                str = "升级订单";
                break;
            case 3:
                str = "手动创建";
                break;
            default:
                str = "";
        }
        return str;
    }


    /**
     * 取消原因 1:用户自己取消 2:超时取消 3:后台手动取消 4:库存不足 5:商品下架
     * @param type 11
     * @return 111
     */
    public static String getCancelType(int type){
        String str;
        switch (type) {
            case 1:
                str = "用户自己取消";
                break;
            case 2:
                str = "超时取消";
                break;
            case 3:
                str = "后台手动取消";
                break;
            case 4:
                str = "库存不足";
                break;
            case 5:
                str = "商品下架";
                break;
            default:
                str = "";
        }
        return str;
    }
}

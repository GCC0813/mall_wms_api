package com.mall.wms.util;

import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author GCC
 * create on 2020/3/6 16:35
 */
public class DateUtil {

    public static String date2Format(Date date, String format){
        if(StringUtils.isBlank(format)){
            format="yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(format).format(date);
    }
}

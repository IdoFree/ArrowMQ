package com.yundan.arrowmq.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Ido on 2017/8/7.
 */
public class DateUtil {
    public static Date toDate(long timestamp){
        if(timestamp == 0){
            return null;
        }
        return new Date(timestamp);

    }


    public static String  toYyyyMMdd(long timestamp){
        if(timestamp == 0){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(timestamp));

    }

    public static String  toYyyyMMdd_HHmmss(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);

    }

    public static String  toYyyyMMdd_HHmmss(long timestamp){
        if(timestamp == 0){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timestamp));

    }


    public static String  toYyyyMMdd(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);

    }

    public static String  toYyyyMMddIso(long timestamp){
        if(timestamp == 0){
            return null;
        }
        TimeZone tz = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(tz);
        return sdf.format(new Date(timestamp));

    }

    public static long toTimeStamp(Date d){
        return d.getTime();
    }


}

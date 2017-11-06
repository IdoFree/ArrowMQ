package com.yundan.arrowmq.util;

/**
 * Created by Ido on 2017/8/9.
 */
public class NumberUtil {
    public static  Number getZeroIfNull(Number n){
        if(n == null){
            return 0;
        }
        return n;
    }
}

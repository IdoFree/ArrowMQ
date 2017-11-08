package com.yundan.arrowmq.core;

import java.util.HashMap;
import java.util.Map;

public class MessageRepository {
    private static Map<Object, String  > repository =  new HashMap<>();
    private static Map<Object, Object  > st =  new HashMap<>();

    public static String getMessage(Object channel){
        return repository.get(channel);
    }
    public static void  putMessage(Object c , String m){
        repository.put(c,m);
    }

    public static void put( Object c ,Object o){
        st.put(c,o);
    }

    public static Object get(Object o){
        return st.get(o);
    }
}

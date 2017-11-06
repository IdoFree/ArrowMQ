package com.yundan.arrowmq.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ido on 2017/8/9.
 */
public class CollectionUtil {
    /**
     * union是指在两个集合中所有数据
     * @param e1
     * @param e2
     * @param <E>
     * @return
     */

    public  static <E extends Iterable> Collection<E> union(Collection<E> e1 , Collection<E> e2){
        Collection<E> reusltSet = new ArrayList<>(e1.size()+e2.size()) ;
        reusltSet.addAll(e1);
        reusltSet.addAll(e2);
        return reusltSet;
    }

    /**
     * INTERSECT是指在两个集合中都存在的数据
     * @param e1
     * @param e2
     * @param <E>
     * @return
     */
    public  static <E extends Iterable> Collection<E> intersect(Collection<E> e1 , Collection<E> e2){
        Collection<E> reusltSet = new ArrayList<>() ;

        e1.forEach(e->{
            if(e2.contains(e)){
                reusltSet.add(e);
            }
        });

        return reusltSet;
    }

    /**
     *  EXCEPT是指在第一个集合中存在，但是不存在于第二个集合中的数据。
     * @param e1
     * @param e2
     * @return
     */
    public  static <E> Collection except(Collection<E> e1 , Collection<E> e2){
        Collection<E> reusltSet = new ArrayList<>() ;

        e1.forEach(e->{
            if(!e2.contains(e)){
                reusltSet.add(e);
            }
        });

        return reusltSet;
    }

    public static boolean isEmpty(Collection e1){
        if(e1 == null || e1.isEmpty()) return true;
        return false;
    }

    public static boolean notEmpty(Collection e1){
        return !isEmpty(e1);
    }

 }

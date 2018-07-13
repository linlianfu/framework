package cn.llf;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: eleven
 * @since: 2018/7/11 21:10
 * @description:
 */
public class AtomicIntegerLocal {
    public static void main(String[] arg){
        AtomicInteger i = new AtomicInteger();
        for (int j = 0;j<10;j++){
            System.out.println(i.getAndIncrement());
        }
    }
}

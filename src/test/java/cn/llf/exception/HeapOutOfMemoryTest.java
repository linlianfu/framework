package cn.llf.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: eleven
 * @since: 2018/7/22 12:08
 * @description:  执行磁带阿门导致
 */
public class HeapOutOfMemoryTest {
    public static void main(String[] arg){
        List<String> strs = new ArrayList<>(10000_0000);
        for(int i = 0 ;i <= 10000_0000; ++ i){
            strs.add(Integer.toString(i));
            if(i % 10000 == 0){
                System.out.println(i);
            }
        }
    }
}

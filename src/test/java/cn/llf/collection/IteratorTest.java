package cn.llf.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: eleven
 * @since: 2018/7/23 22:00
 * @description:
 */
public class IteratorTest {

    public static void main(String args[]){
        List<String> list = new ArrayList<>();
        String temp = "2";
        list.add("1");
        list.add(temp);
        list.add(temp);
        list.add(temp);
        list.add(temp);
        Iterator<String> iterator = list.iterator();



        for (int i = 0; i < list.size(); i++) {

            System.out.println(i);
            System.out.println(list.size());
            list.remove(i);
        }
        System.out.println("最总list.size："+list.size());


        //执行通过
//        for (;iterator.hasNext();){
//            System.out.println(iterator.next());
//            iterator.remove();
//        }
//        System.out.println("iterator循环删除后的集合长度："+list.size());


         //增强for循环报错
        //java.util.ConcurrentModificationException
//
//        for (String s : list) {
//            list.remove(s);
//            System.out.println(list.size());
//        }

        //执行异常：java.util.ConcurrentModificationException
//        list.forEach(p->{
//            list.remove(p);
//        });

//        Set<String> set = new HashSet();
//        set.add(temp);
//        set.add(temp);
//        set.add(temp);
//        System.out.println(set.size());


    }
}

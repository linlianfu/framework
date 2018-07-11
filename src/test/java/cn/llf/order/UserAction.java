package cn.llf.order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: eleven
 * @since: 2018/7/11 14:15
 * @description:
 */
public class UserAction {

    public static void main(String[] arg){
        User user = new User(10,"w",1);
        User user1 = new User(11,"m",2);
        User user2 = new User(12,"w",1);
        User user3 = new User(11,"m",2);
        User user4 = new User(13,"w",1);
        User user5 = new User(11,"w",1);
        List<User> temp = new ArrayList<>();
        temp.add(user);
        temp.add(user1);
        temp.add(user2);
        temp.add(user3);
        temp.add(user4);
        temp.add(user5);

        //1.集合框架提供的Collections.sort实现排序
//        Collections.sort(temp);

//        Collections.sort(temp, new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return o1.getAge()-o2.getAge();
//            }
//        });

        //2利用Java8的stream流和Comparator实现集合排序
//        temp = temp.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());

//        temp = temp.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());


        temp =  temp.stream().sorted(Comparator.comparing(User::getAge)
                                      .reversed()
                                      .thenComparing(Comparator.comparing(User::getGrade).reversed())
                            ).collect(Collectors.toList());
        for (User item : temp) {
            System.out.println(item);
        }

        //3数组排序，利用Arrays.sort实现数组排序
//        User[] arr = new User[]{user1,user,user2};
//        Arrays.sort(arr);
//        for (User user3 : arr) {
//            System.out.println(user3);
//        }
    }

}

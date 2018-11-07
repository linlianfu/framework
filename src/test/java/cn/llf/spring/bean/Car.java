package cn.llf.spring.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * @author eleven
 * @date 2018/11/6
 * @description
 */
@Data
@Slf4j
public class Car {
    /**
     * 名字
     */
    private String name;
    /**
     * 数量
     */
    private int count;

    public void introduce(){
        log.info("name:{},count:{}",name,count);
    }


    public static void main(String[] arg){
        Car car = new Car();
        car.setName("轿车");
        car.setCount(1);
        car.introduce();
    }

}

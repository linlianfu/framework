package cn.llf.framework.services.JavaSE;

/**
 * @author: eleven
 * @date: 2018/8/30 22:21
 * @description: 上下文顶级接口类，验证匿名内部类，在接口调用的时候直接new，而不用具体实现类
 */
public interface ApplayContext {


    String say(String name,int i);
}

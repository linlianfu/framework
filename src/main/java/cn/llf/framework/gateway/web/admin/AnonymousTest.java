package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.services.JavaSE.ApplayContext;

/**
 * @author: eleven
 * @since: 2018/6/5 16:20
 * @description:匿名内部类学习
 */
public class AnonymousTest {


    public int i = 5;

    public static void main(String[] arg){
//         String name = "2";
//         int i = 3;
//        AnonymousTest anonymousTest = new AnonymousTest();
//        anonymousTest.outMethod("AnonymousTest");
//        AnonymousTest.InnerClass innerClass = anonymousTest.new InnerClass(""){
//
//            @Override
//            public void say() {
//                System.out.println(name);
//            }
//
//            @Override
//            public void outMethod(String name1) {
//                super.outMethod(name);
//                System.out.println(i);
//                System.out.println(name);
//            }
//        };
//        innerClass.outMethod(name);

        say(
                //直接实现ApplayContext的接口，实现其中的say方法。其中a,b对应代表两个参数
                //注意：如果接口又多个方法，则不能使用lambada表达式，需要用new属性来new出匿名内部类
                (a,b) -> {
                    System.out.println("a:"+a);
                    System.out.println("b:"+b);
                    return "";
                 }
        );
    }



    public void outMethod(String name){
        i = 3;
        System.out.println("调用方："+name+"--out 打印");
    }
    public void outMethod2(){
        System.out.println("outMethod2 打印");
    }



    public static  void say(ApplayContext context){
        //java多态应用，形参为接口，实参则为具体实现类，调用具体实现类的say()方法
        context.say("1111",1);
    }

    public abstract class  InnerClass{
        InnerClass(String name){

        }

        public abstract void say();

        public void outMethod(String name){
            i = 5;
            name = "2";
            System.out.println("inner 打印");
            AnonymousTest.this.outMethod("InnerClass");
            outMethod2();
        }
    }
}

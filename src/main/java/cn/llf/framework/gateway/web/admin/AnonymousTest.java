package cn.llf.framework.gateway.web.admin;

/**
 * @author: eleven
 * @since: 2018/6/5 16:20
 * @description:匿名内部类学习
 */
public class AnonymousTest {


    public int i = 5;

    public static void main(String[] arg){
         String name = "2";
         int i = 3;
        AnonymousTest anonymousTest = new AnonymousTest();
        anonymousTest.outMethod("AnonymousTest");
        AnonymousTest.InnerClass innerClass = anonymousTest.new InnerClass(){

            @Override
            public void say() {
                System.out.println(name);
            }

            @Override
            public void outMethod(String name1) {
                super.outMethod(name);
                System.out.println(i);
                System.out.println(name);
            }
        };
        innerClass.outMethod(name);
    }



    public void outMethod(String name){
        i = 3;
        System.out.println("调用方："+name+"--out 打印");
    }
    public void outMethod2(){
        System.out.println("outMethod2 打印");
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

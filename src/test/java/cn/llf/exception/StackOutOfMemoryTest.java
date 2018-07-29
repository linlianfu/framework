package cn.llf.exception;

/**
 * @author: eleven
 * @since: 2018/7/22 12:10
 * @description:
 */
public class StackOutOfMemoryTest {
    public static void main(String[] args) throws Exception{
        new StackOutOfMemoryTest().miao();
    }

    public void miao(){
        long time = System.currentTimeMillis();
        miao();

    }
}

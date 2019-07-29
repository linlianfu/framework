package cn.llf.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author eleven
 * @date 2019/7/28
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:common.xml")
public class LockTest {

    private  int totalTicket = 10;

    private final Object objectLock = new Object();


    /**
     * 测试synchronized的应用
     */
    @Test
    public void synchronizedLock(){

        TicketWindow firstWindow = new TicketWindow(1);
        TicketWindow secondWindow = new TicketWindow(2);
        TicketWindow thirdWindow = new TicketWindow(3);
        firstWindow.start();
        secondWindow.start();
        thirdWindow.start();
        while (true){
            //子线程已经结束，这里为什么不会退出循环？因为每个线程只是修改自己本地内存的值？主内存还是修改器按的值？
            //当totalTicket使用了volatile修饰之后，不会出现死循环
            // 由此引发另外一个问题:线程内存修改的变量什么时候会同步给煮内存？
            //死循环结论：当没有使用volatile修饰是，这里涉及指令重排，
            // 这里会被优化为  if (totalTicket <= 0){while(true)},所以导致了死循环
            if (totalTicket <= 0){
                log.info(">>>>>>>>");
                break;
            }
//            TicketWindow window = new TicketWindow(4);
        }
        log.info(">> 暂无余票，谢谢！");

    }

    /**
     * 测试synchronized的实现和应用
     */
    public class TicketWindow extends Thread{
        /**
         * 窗口编号
         */
        private int windowNumber;

        TicketWindow(int windowNumber){
            this.windowNumber = windowNumber;

        }

        @Override
        public void run() {
            log.info(">> 窗口[{}]开始售票",windowNumber);
            while (true){
                synchronized (objectLock){
                    if (totalTicket <= 0){
                        break;
                    }
                    totalTicket-- ;
                    log.info(">> 窗口[{}]售卖了第[{}]张票",windowNumber,totalTicket);
                }
            }
            log.info(">> 窗口[{}]结束售票，余票[{}]",windowNumber,totalTicket);
        }
    }


    /**
     * jdk自带的重入锁的demo
     */
    @Test
    public void reentrantLockTest(){

        //使用公平锁，实现线程轮流获取锁
        Lock lock = new ReentrantLock(true);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Ticket first = new Ticket(1,countDownLatch,lock);
        Ticket second = new Ticket(2,countDownLatch,lock);
        Ticket third = new Ticket(3,countDownLatch,lock);
        first.start();
        second.start();
        third.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(">>>>>售票完成");

    }

    public class Ticket extends Thread{
        /**
         * 窗口编号
         */
        private int windowNumber;
        private CountDownLatch countDownLatch;
        /**
         * 外部调用传入参数
         */
        private Lock lock;

        Ticket(int windowNumber,CountDownLatch countDownLatch,Lock lock){
            this.windowNumber = windowNumber;
            this.countDownLatch = countDownLatch;
            this.lock = lock;
        }

        @Override
        public void run() {
            log.info(">> 窗口[{}]开始售票",windowNumber);
            while (true){
                boolean acquire = false;
                try {
                    acquire = lock.tryLock(2, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (acquire) {
                    try {
                        if (totalTicket <= 0) {
                            break;
                        }
                        totalTicket--;
                        log.info(">> 窗口[{}]售卖了第[{}]张票", windowNumber, totalTicket);
                    } finally {
                        log.info("窗口[{}]释放锁", windowNumber);
                        lock.unlock();
//                        try {
//                            TimeUnit.MILLISECONDS.sleep(500);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            }
            log.info(">> 窗口[{}]结束售票，余票[{}]",windowNumber,totalTicket);
            countDownLatch.countDown();
        }
    }




    /**
     * 测试响应中断
     */
    @Test
    public void testResponseInterrupted(){
        Lock lock = new ReentrantLock(true);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        InterruptThread thread = new InterruptThread(1,lock,countDownLatch);
        InterruptThread thread1 = new InterruptThread(2,lock,countDownLatch);
        thread.start();
        thread1.start();


        try {
            TimeUnit.SECONDS.sleep(1);
            thread.interrupt();
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(">> 主线程结束");
    }

    /**
     * 测试中断响应
     */
    class InterruptThread extends Thread{

        /**
         * 线程使用的锁
         */
        private  Lock lock;

        /**
         * 线程编号
         */
        private int threadNumber;
        /**
         * 线程计数器
         */
        private CountDownLatch countDownLatch;

        InterruptThread (int threadNumber,Lock lock,CountDownLatch countDownLatch){
            this.lock = lock;
            this.threadNumber = threadNumber;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                lock.lockInterruptibly();
                log.info(">> 线程[{}]获取锁",threadNumber);
                log.info("测试响应中断，线程进入睡眠时间");
                TimeUnit.SECONDS.sleep(3);
                log.info(">> 线程[{}]正常结束",threadNumber);
            } catch (InterruptedException e) {
                log.info("线程编号[{}]中断异常，被动结束线程",threadNumber);
                e.printStackTrace();
            }finally {
                lock.unlock();
                countDownLatch.countDown();
                log.info(">> 线程[{}]释放锁",threadNumber);
            }

        }
    }


}

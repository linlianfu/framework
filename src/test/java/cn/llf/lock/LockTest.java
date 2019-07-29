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


    @Test
    public void synchronizedLock(){

        TicketWindow firstWindow = new TicketWindow(1);
        TicketWindow secondWindow = new TicketWindow(2);
        TicketWindow thirdWindow = new TicketWindow(3);
        firstWindow.start();
        secondWindow.start();
        thirdWindow.start();
        while (true){
            //子线程已经结束，这里为什么不会退出循环？因为每个线程只是修改自己本地内存的值？主内存还是修改之前的值？
            //当totalTicket使用了volatile修饰之后，不会出现死循环，保证了可见性
            // 由此引发另外一个问题:当共享变量没有使用 volatile 修饰的时候，
            // 线程内存修改的变量什么时候会同步给主内存？

            //最终问题根源：没有使用volatile修饰共享变量，jvm进行指令重排，
            // 重排结果为：if(totalTicket <= 0){while(true){}},所以导致了死循环，不会退出循环
            if (totalTicket <= 0){
//                https://ask.csdn.net/questions/242833
                log.info(">>>>>>>>");
                break;
            }
//            TicketWindow window = new TicketWindow(4);
        }
        log.info(">> 暂无余票，谢谢！");

    }


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



    private Lock lock = new ReentrantLock();
    @Test
    public void reentrantLockTest(){
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Ticket first = new Ticket(1,countDownLatch);
        Ticket second = new Ticket(2,countDownLatch);
        Ticket third = new Ticket(3,countDownLatch);
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

        Ticket(int windowNumber,CountDownLatch countDownLatch){
            this.windowNumber = windowNumber;
            this.countDownLatch = countDownLatch;

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

}

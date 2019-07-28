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

    private int totalTicket = 10;

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
            //子线程已经结束，这里为什么不会退出循环？
            if (totalTicket <= 0){
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

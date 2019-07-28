package cn.llf.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

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
    public void lock(){
        TicketWindow firstWindow = new TicketWindow(1);
        TicketWindow secondWndow = new TicketWindow(2);
        TicketWindow thirdWindow = new TicketWindow(3);
        firstWindow.start();
        secondWndow.start();
        thirdWindow.start();
        while (true){
            if (totalTicket <= 0){
                log.info(">>>>>>>>");
                break;
            }else {
                log.info("上有余票");
            }
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
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    totalTicket-- ;
                    log.info(">> 窗口[{}]售卖了第[{}]张票",windowNumber,totalTicket);
                }
            }
            log.info(">> 窗口[{}]结束售票",windowNumber);
        }
    }
}

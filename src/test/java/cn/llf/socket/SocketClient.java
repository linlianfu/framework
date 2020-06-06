package cn.llf.socket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author eleven
 * @date 2020/6/6
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:common.xml")
public class SocketClient {


    @Test
    public void sendMessage(){
        try {
            // 要连接的服务端IP地址和端口
            String host = "127.0.0.1";
            int port = 8080;
            // 与服务端建立连接
            Socket socket = new Socket(host, port);
            // 建立连接后获得输出流
            OutputStream outputStream = socket.getOutputStream();
            String message="来自客户端的消息";
            socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
            socket.close();
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}

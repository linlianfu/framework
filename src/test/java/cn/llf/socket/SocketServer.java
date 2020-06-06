package cn.llf.socket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
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
public class SocketServer {

    @Test
    public void serverListener(){

        try {
            ServerSocket server = new ServerSocket(8080);
            log.warn(">> 服务端等待客户端发送消息");
            Socket socket = server.accept();
            // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
            }
            log.warn("收到来自客户端的请求: " + sb);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("客户端，你好，我已确认收到你的消息".getBytes(StandardCharsets.UTF_8));

            outputStream.close();
            inputStream.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}

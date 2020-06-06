package cn.llf.socket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
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
            String message="服务端，你好，我已经到站了";
            socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
            // 告诉服务端，客户端已经发送完成，接下来只能接受收据
            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
            }
            log.warn("收到来自服务端的回应: " + sb);

            inputStream.close();
            outputStream.close();
            socket.close();
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}

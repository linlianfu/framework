package cn.llf.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author eleven
 */
@Slf4j
public class NIOServer {

    private Selector selector;


    @Test
    public void receive() throws IOException {
        NIOServer server = new NIOServer();
        server.init();
        server.start();
    }


    public void init() throws IOException {
        this.selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        ServerSocket serverSocket = channel.socket();
        InetSocketAddress address = new InetSocketAddress(8080);
        serverSocket.bind(address);
        channel.register(this.selector, SelectionKey.OP_ACCEPT);
    }

    public void start() throws IOException {
        log.warn(">> server start");
        while (true){
            selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isAcceptable()){
                    accept(key);
                }else if (key.isReadable()){
                    read(key);
                }
            }
        }
    }

    public void accept(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel)key.channel();
        SocketChannel channel = server.accept();
        if(channel != null){
            channel.configureBlocking(false);
            channel.register(this.selector,SelectionKey.OP_READ);
        }
    }
    public void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel)key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        String request = new String(buffer.array()).trim();
        log.warn("客户端请求：{}",request);
        ByteBuffer outBuffer = ByteBuffer.wrap("服务端请求收到".getBytes());
        channel.write(outBuffer);
    }
}

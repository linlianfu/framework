package cn.llf.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author eleven
 */
@Slf4j
public class NIOClient {
    private Selector selector;
    private final BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] age) throws IOException {
        NIOClient client = new NIOClient();
        client.init();
        client.start();
    }

    public void init() throws IOException {
        this.selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("127.0.0.1", 8080));
        channel.register(this.selector, SelectionKey.OP_CONNECT);
    }

    public void start() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isConnectable()) {
                    connect(key);
                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    public void connect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.isConnectionPending()) {
            if (channel.finishConnect()) {
                channel.configureBlocking(false);
                channel.register(this.selector, SelectionKey.OP_READ);
                String request = clientInput.readLine();
                channel.write(ByteBuffer.wrap(request.getBytes()));
            } else {
                key.cancel();
            }
        }
    }

    public void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        String response = new String(buffer.array()).trim();
        log.warn("服务端响应：{}", response);
        String nextRequest = clientInput.readLine();
        ByteBuffer outBuffer = ByteBuffer.wrap(nextRequest.getBytes());
        channel.write(outBuffer);
    }
}

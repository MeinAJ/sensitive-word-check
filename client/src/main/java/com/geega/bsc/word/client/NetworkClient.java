package com.geega.bsc.word.client;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class NetworkClient {

    private final Selector selector;
    private final SocketChannel socketChannel;

    /**
     * 0：初始化状态 1：建立连接 2：连接断开
     */
    private volatile int connectionState;

    public NetworkClient(String serverIp, int serverPort) {

        try {

            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(serverIp, serverPort));
            socketChannel.configureBlocking(false);
            socketChannel.socket().setTcpNoDelay(true);
            socketChannel.socket().setReceiveBufferSize(1024 * 8);
            socketChannel.socket().setKeepAlive(true);
            selector = Selector.open();

            //阻塞等待建立好连接
            while (true) {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
                int select = selector.select();
                if (select > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if (selectionKey.isConnectable()) {
                            if (socketChannel.isConnectionPending()) {
                                if (socketChannel.finishConnect()) {
                                    connectionState = 1;
                                    selectionKey.attach(socketChannel);
                                    //已经创建好连接了，将read事件注册到selector中，移除connect事件
                                    selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_READ);
                                    selectionKey.interestOps(selectionKey.interestOps() & ~SelectionKey.OP_CONNECT);
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            }
        } catch (Exception e) {
            connectionState = 2;
            throw new RuntimeException("创建连接失败", e);
        }
    }

    public boolean isConnected() {
        return connectionState == 1;
    }

    class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    int select = selector.select();
                    if (select > 0) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            iterator.remove();

                            if (selectionKey.isReadable()) {

                            } else if (selectionKey.isWritable()) {

                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("处理事件异常", e);
                }
            }
        }
    }
}

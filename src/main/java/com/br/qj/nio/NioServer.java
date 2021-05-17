package com.br.qj.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NioServer {

    public static void main(String[] args) {
        NioServer nioServer = new NioServer();
        nioServer.startServer(9999);
    }

    private void startServer(int port) {
        try {
            //打开监听通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //传入端口，并设定连接队列最大为1024
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            List<SocketChannel> channelList = new ArrayList<SocketChannel>();
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    System.out.println("一个连接建立。。。。");
                    socketChannel.configureBlocking(false);
                    channelList.add(socketChannel);
                }
                Iterator<SocketChannel> iterator = channelList.iterator();
                while (iterator.hasNext()) {
                    SocketChannel sc = iterator.next();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len = sc.read(byteBuffer);
                    if (len > 0) {
                        System.out.println("you got an message:" + new String(byteBuffer.array(), 0, len));
                    } else if (len == -1){
                        sc.close();
                        iterator.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.br.qj.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio 服務端
 */
public class BioServer {

    public static void main(String[] args) {
        BioServer bioServer = new BioServer();
        bioServer.initBIOServer(9999);
    }

    public void initBIOServer(int port) {
        //服务端Socket
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                //客户端socket
                Socket socket = serverSocket.accept();  // 阻塞等待 等待一个新的连接
//                if (socket !=  null)  new Thread(new BioReadThread(socket)).start(); // 优化处理
                try {
                    while (true) {
                        byte[] bytes = new byte[1024];
                        int read = socket.getInputStream().read(bytes);// 阻塞等待 等待读取这个连接发送的数据
                        System.out.println("read完毕。。");
                        if (read >= 0) {
                            System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
                            socket.getOutputStream().write("HelloClient".getBytes());
                            socket.getOutputStream().flush();
                        } else if (read == -1) {
                            System.out.println("连接关闭了。。。。。");
                            socket.close();
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

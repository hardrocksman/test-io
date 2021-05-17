package com.br.qj.bio;

import java.net.Socket;

public class BioReadThread implements Runnable {

    private Socket socket;

    public BioReadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while(true) {
            try {
                byte[] bytes = new byte[1024];
                int read = socket.getInputStream().read(bytes);
                System.out.println("read完毕。。");
                if (read != -1) {
                    System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
                    socket.getOutputStream().write("HelloClient".getBytes());
                    socket.getOutputStream().flush();
                } else {
                    System.out.println("连接关闭了.......");
                    socket.close();
                    break;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

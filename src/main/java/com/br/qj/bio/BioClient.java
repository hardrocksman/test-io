package com.br.qj.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * bio 客戶端
 */
public class BioClient {

    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("localhost", 9999);
        //向服务端发送数据
        socket.getOutputStream().write("HelloServer".getBytes());
        socket.getOutputStream().flush();
        System.out.println("向服务端发送数据结束");
        byte[] bytes = new byte[1024];
        //接收服务端回传的数据
        int read = socket.getInputStream().read(bytes);
        System.out.println("接收到服务端的数据：" + new String(bytes, 0, read));
        socket.close();
    }
}

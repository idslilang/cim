package com.crossoverjie.demo.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        new Thread(()->{
            while (true){
                try {
                    Socket socket = serverSocket.accept();
                    new Thread(()->{
                        byte[] data = new byte[1024];
                        try {
                            InputStream inputStream = socket.getInputStream();
                            while (true){
                                int len;
                                // (3) 按字节流方式读取数据
                                while ((len = inputStream.read(data)) != -1) {
                                    System.out.println(new String(data, 0, len));
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        System.out.println("哈啊哈哈");
    }
}

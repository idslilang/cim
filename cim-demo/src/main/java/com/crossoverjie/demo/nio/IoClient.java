package com.crossoverjie.demo.nio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class IoClient {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("127.0.0.1",8000);
                    while (true){
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        socket.getOutputStream().flush();
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

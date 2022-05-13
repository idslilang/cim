package com.crossoverjie.demo.nio;

import java.io.IOException;
import java.nio.channels.Selector;

//https://www.jianshu.com/p/a4e03835921a
public class NioServer {

    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(new Runnable() {
            @Override
            public void run() {


            }
        }).start();
    }
}

package com.crossoverjie.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

//https://www.jianshu.com/p/a4e03835921a
public class NioServer {

    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                    listenerChannel.socket().bind(new InetSocketAddress(8000));
                    listenerChannel.configureBlocking(false);
                    listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                    while (true) {
                        if (serverSelector.select(1) > 0) {

                            Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
                            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

                            while (keyIterator.hasNext()) {
                                SelectionKey key = keyIterator.next();

                                if (key.isAcceptable()) {

                                    try {
                                        SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();

                                        clientChannel.configureBlocking(false);
                                        clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                    } finally {
                                        selectionKeys.remove(key);
                                    }
                                }
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        if (clientSelector.select(1)>0){
                            Set<SelectionKey> set = clientSelector.selectedKeys();
                            Iterator<SelectionKey> keyIterator = set.iterator();
                            while (keyIterator.hasNext()){
                                SelectionKey key = keyIterator.next();
                                if (key.isReadable()){
                                    try {
                                        SocketChannel clientChannel = (SocketChannel) key.channel();
                                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                        clientChannel.read(byteBuffer);
                                        byteBuffer.flip();
                                        System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                                    }finally {
                                        set.remove(key);
                                        key.interestOps(SelectionKey.OP_READ);
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

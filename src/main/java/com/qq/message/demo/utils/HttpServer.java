package com.qq.message.demo.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer extends ServerSocket {
    public HttpServer(int port) throws IOException {
        super(port);
        try {
            while(true) {
                Socket socket = accept();
                new ServerThread(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(); // 关闭监听端口
        }
    }
}

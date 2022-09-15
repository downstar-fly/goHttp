package com.qq.message.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ServerThread(Socket s) throws IOException {
        this.socket = s;
        // 构造该会话中的输入输出流
        reader = new BufferedReader(new InputStreamReader(socket
                .getInputStream(), "utf-8"));
        writer = new PrintWriter(socket.getOutputStream(),true);
        start();
    }

    public void run () {
        try {
            // Communicate with client until "bye "received.
            while (true) {
                // 通过输入流接收客户端信息
                String line = reader.readLine();
                if (line == null || "".equals(line.trim())) { //是否终止会话
                    break;
                }
                System.out.println("Received  message: " + line);
                // 通过输出流向客户端发送信息
                writer.println(line);
                writer.flush();
            }

            writer.close();
            reader.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

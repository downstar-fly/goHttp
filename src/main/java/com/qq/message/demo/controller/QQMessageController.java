package com.qq.message.demo.controller;

import com.qq.message.demo.config.RequestConfig;
import com.qq.message.demo.config.RequestUrl;
import com.qq.message.demo.service.QQMessageService;
import com.qq.message.demo.service.impl.QQMessageServiceImpl;
import com.qq.message.demo.utils.HttpClient;
import com.qq.message.demo.utils.HttpServer;
import com.qq.message.demo.utils.WebSocketConfig;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
public class QQMessageController {

    private QQMessageService qqMessageService = new QQMessageServiceImpl();

    public void sendMessgaeToUser() {
        String user_id = "1954914724";
        String message = "别在这理发店";
        qqMessageService.sendMessgaeToUser(user_id, message);
    }

    public static void main(String[] args) {
//        QQMessageController controller = new QQMessageController();
//        controller.sendMessgaeToUser();
//        HttpClient.portListener(5700);
        WebSocketConfig webSocketConfig = new WebSocketConfig();
        WebSocketClient webSocketClient = webSocketConfig.webSocketClient();
//        webSocketClient.;
    }
}

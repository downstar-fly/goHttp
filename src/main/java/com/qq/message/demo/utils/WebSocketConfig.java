package com.qq.message.demo.utils;

import com.alibaba.fastjson.JSON;
import com.qq.message.demo.config.RequestConfig;
import com.qq.message.demo.entry.GroupMessage;
import com.qq.message.demo.service.QQMessageService;
import com.qq.message.demo.service.impl.QQMessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.net.URI;

@Slf4j
@Component
public class WebSocketConfig {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private QQMessageService qqMessageService = new QQMessageServiceImpl();

    @Bean
    public WebSocketClient webSocketClient() {
        try {
            WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://127.0.0.1:5701"),new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    log.info("[websocket] 连接成功");
                }

                @Override
                public void onMessage(String message) {
                    log.info(message);
//                    qqMessageService.recallMessage(message);
//                    qqMessageService.fireMessage(message);
                    qqMessageService.tulingMessage(message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    log.info("[websocket] 退出连接");
                }

                @Override
                public void onError(Exception ex) {
                    log.info("[websocket] 连接错误={}",ex.getMessage());
                }
            };
            webSocketClient.connect();
            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

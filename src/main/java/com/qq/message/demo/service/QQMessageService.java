package com.qq.message.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface QQMessageService {
    void sendMessgaeToUser(String user_id, String message);

    void recallMessage(int message_id);
}

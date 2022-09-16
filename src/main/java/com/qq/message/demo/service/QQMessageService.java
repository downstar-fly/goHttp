package com.qq.message.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface QQMessageService {
    void sendMessgaeToUser(String user_id, String message);

    void recallMessage(String message);

    void sendMessageToGroup(int group_id, String message);

    void fireMessage(String message);

    void tulingMessage(String message);
}

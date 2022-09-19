package com.qq.message.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface QQMessageService {
    // 私聊
    void sendMessgaeToUser(String user_id, String message);

    // 撤回消息 目前支持撤回带有男同的消息
    void recallMessage(String message);

    // 群聊
    void sendMessageToGroup(int group_id, String message);

    // 回怼
    void fireMessage(String message);

    // 图灵机器人自动回复
    void tulingMessage(String message);

    //图灵机器人自动回复查找图片
    void tulingImgMessage(int group_id, String message);

    //搜题
    void queryAnswer(int group_id, String message);
}

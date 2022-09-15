package com.qq.message.demo.service.impl;

import com.qq.message.demo.DO.ApiParams;
import com.qq.message.demo.config.RequestConfig;
import com.qq.message.demo.config.RequestUrl;
import com.qq.message.demo.service.QQMessageService;
import com.qq.message.demo.utils.HttpClient;
import org.springframework.stereotype.Service;

@Service("QQMessageService")
public class QQMessageServiceImpl implements QQMessageService {
    @Override
    public void sendMessgaeToUser(String user_id, String message) {
        ApiParams apiParams = new ApiParams();
        apiParams.setUser_id(user_id);
        apiParams.setMessage(message);
        apiParams.setGroup_id("");
        apiParams.setAuto_escape(false);
        String url = RequestUrl.SendMessageToUser.getUri(apiParams);
        String res = HttpClient.doGet(url);
        System.out.println(res);
    }

    @Override
    public void recallMessage(int message_id) {
        ApiParams apiParams = new ApiParams();
        apiParams.setMessage_id(message_id);
        String url = RequestUrl.RecallMessage.getUri(apiParams);
        HttpClient.doGet(url);
    }
}

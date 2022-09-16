package com.qq.message.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.qq.message.demo.DO.ApiParams;
import com.qq.message.demo.config.RequestConfig;
import com.qq.message.demo.config.RequestUrl;
import com.qq.message.demo.entry.GroupMessage;
import com.qq.message.demo.entry.PostJson;
import com.qq.message.demo.entry.SendGroupMessage;
import com.qq.message.demo.entry.User;
import com.qq.message.demo.entry.tuling.DO.InputText;
import com.qq.message.demo.entry.tuling.DO.Perception;
import com.qq.message.demo.entry.tuling.DO.TulingOpenapiDO;
import com.qq.message.demo.entry.tuling.DO.UserInfo;
import com.qq.message.demo.entry.tuling.VO.TulingOpenApiVO;
import com.qq.message.demo.service.QQMessageService;
import com.qq.message.demo.utils.HttpClient;
import com.qq.message.demo.utils.PinyinUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("QQMessageService")
public class QQMessageServiceImpl implements QQMessageService {
    @Override
    public void sendMessgaeToUser(String user_id, String message) {
        ApiParams apiParams = new ApiParams();
        apiParams.setUser_id(user_id);
        apiParams.setMessage(message);
        apiParams.setGroup_id(-1);
        apiParams.setAuto_escape(false);
        String url = RequestUrl.SendMessageToUser.getUri(apiParams);
        String res = HttpClient.doGet(url);
        System.out.println(res);
    }

    @Override
    public void recallMessage(String message) {
        GroupMessage groupMessage = JSON.parseObject(message, GroupMessage.class);
        if (groupMessage.getGroup_id() == RequestConfig.GroupId) {
            String res = PinyinUtil.getPinyin(groupMessage.getMessage().toString(), "");
            if (res.indexOf("nanton") > -1) {
                ApiParams apiParams = new ApiParams();
                apiParams.setMessage_id(groupMessage.getMessage_id());
                String url = RequestUrl.RecallMessage.getUri(apiParams);
                HttpClient.doGet(url);
            }
        }

    }

    @Override
    public void sendMessageToGroup(int group_id, String message) {
        SendGroupMessage sendGroupMessage = new SendGroupMessage();
        sendGroupMessage.setGroup_id(group_id);
        sendGroupMessage.setMessage(message);
        sendGroupMessage.setAuto_escape(false);
        String url = RequestUrl.SendMessageToGroup.getUri(new ApiParams());
        String res = HttpClient.doPost(url, JSON.toJSONString(sendGroupMessage));
        System.out.println(res);
    }

    @Override
    public void fireMessage(String message) {
        GroupMessage groupMessage = JSON.parseObject(message, GroupMessage.class);
        if (groupMessage.getMessage().toString().indexOf(RequestConfig.atMe) > -1) {
            List<String> messages = new ArrayList();
            messages.add("莫挨老子");
            messages.add("干嘛？想偷我裤衩");
            messages.add("不要欺负我");
            messages.add("您呼叫的用户不在服务区 请在 bi 声之后留言，bi~");
            messages.add("给你一拳");
            messages.add("是谁 是谁");
            messages.add("抓到你了[CQ:at,qq=" + groupMessage.getUser_id() + "]");
            messages.add("cpdd 自带彩礼");
            messages.add("干嘛？想偷我裤衩");
            messages.add("干嘛？想偷我裤衩");
            Random random = new Random();
            int index = random.nextInt(9) + 1;
            String fireMsg = messages.get(index);
            sendMessageToGroup(groupMessage.getGroup_id(), fireMsg);
        }
    }

    @Override
    public void tulingMessage(String message) {
        GroupMessage groupMessage = JSON.parseObject(message, GroupMessage.class);
        String msg = groupMessage.getMessage().toString();

        if (msg.indexOf(RequestConfig.atMe) > -1) {
            msg = msg.substring(22);
            TulingOpenapiDO openapiDO = new TulingOpenapiDO();
            openapiDO.setReqType(0);

            Perception perception = new Perception();
            InputText inputText = new InputText();
            inputText.setText(msg);
            perception.setInputText(inputText);
            openapiDO.setPerception(perception);

            UserInfo userInfo = new UserInfo();
            userInfo.setApiKey(RequestConfig.apiKey);
            userInfo.setUserId(RequestConfig.userId);
            openapiDO.setUserInfo(userInfo);

            String params = JSON.toJSONString(openapiDO);
            String res = HttpClient.doPost(RequestConfig.tuling, params);
            Object obj = JSON.parseObject(res, Object.class);
            TulingOpenApiVO vo = new TulingOpenApiVO();
//            BeanUtils.copyProperties(obj, vo);
//            vo.setIntent(obj);
            System.out.println(obj);
        }
    }


}

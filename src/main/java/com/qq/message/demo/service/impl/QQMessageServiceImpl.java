package com.qq.message.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.qq.message.demo.DO.ApiParams;
import com.qq.message.demo.config.RequestConfig;
import com.qq.message.demo.config.RequestUrl;
import com.qq.message.demo.entry.GroupMessage;
import com.qq.message.demo.entry.SendGroupMessage;
import com.qq.message.demo.entry.User;
import com.qq.message.demo.entry.answer.DO.QueryAnswerDO;
import com.qq.message.demo.entry.tuling.DO.InputText;
import com.qq.message.demo.entry.tuling.DO.Perception;
import com.qq.message.demo.entry.tuling.DO.TulingOpenApiuImgDO;
import com.qq.message.demo.entry.tuling.DO.TulingOpenapiDO;
import com.qq.message.demo.entry.tuling.DO.UserInfo;
import com.qq.message.demo.entry.tuling.VO.Intent;
import com.qq.message.demo.entry.tuling.VO.Results;
import com.qq.message.demo.entry.tuling.VO.TulingOpenApiVO;
import com.qq.message.demo.service.QQMessageService;
import com.qq.message.demo.utils.HttpClient;
import com.qq.message.demo.utils.PinyinUtil;
import com.qq.message.demo.utils.TulingUtils;
import java.util.Map;
import org.springframework.stereotype.Service;

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
        String res = HttpClient.doPost(url, JSON.toJSONString(sendGroupMessage), false);
        System.out.println(res);
    }

    @Override
    public void fireMessage(String message) {
        GroupMessage groupMessage = JSON.parseObject(message, GroupMessage.class);
        if (groupMessage.getMessage().toString().indexOf(RequestConfig.atMe) > -1) {
            List<String> messages = new ArrayList();
            messages.add("????????????");
            messages.add("????????????????????????");
            messages.add("???????????????");
            messages.add("????????????????????????????????? ?????? bi ??????????????????bi~");
            messages.add("????????????");
            messages.add("?????? ??????");
            messages.add("????????????[CQ:at,qq=" + groupMessage.getUser_id() + "]");
            messages.add("cpdd ????????????");
            messages.add("????????????????????????");
            messages.add("????????????????????????");
            Random random = new Random();
            int index = random.nextInt(9) + 1;
            String fireMsg = messages.get(index);
            sendMessageToGroup(groupMessage.getGroup_id(), fireMsg);
        }
    }

    @Override
    public void tulingMessage(String message) {
        GroupMessage groupMessage = TulingUtils.getMessage(message);
        String msg = groupMessage.getMessage().toString();
        if (msg.contains(RequestConfig.atMe)) {
            String fireMsg = "";
            msg = msg.substring(22);
            TulingOpenapiDO openapiDO = new TulingOpenapiDO();

            // msg.contains(RequestConfig.imgMsg)
            if (msg.contains(RequestConfig.imgMsg)) {
                tulingImgMessage(groupMessage.getGroup_id(), msg);
            } else {
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

                // ???????????????????????????go-http?????????????????????
                String params = JSON.toJSONString(openapiDO);
                String res = HttpClient.doPost(RequestConfig.tuling, params, false);
                Map<String, String> map = JSON.parseObject(res, Map.class);


                TulingOpenApiVO vo = new TulingOpenApiVO();
                List<Results> results = JSON.parseArray(JSON.toJSONString(map.get("results")), Results.class);
                vo.setResults(results);
                fireMsg = "[CQ:at,qq=" + groupMessage.getUser_id() + "]  " + vo.getResults().get(0).getValues().getText();
            }

            sendMessageToGroup(groupMessage.getGroup_id(), fireMsg);
        }
    }

    @Override
    public void tulingImgMessage(int group_id, String message) {
        TulingOpenApiuImgDO imgDO = new TulingOpenApiuImgDO();
        imgDO.setKey(RequestConfig.apiKey);
        imgDO.setInfo(message);
        String res = HttpClient.doPost(RequestConfig.tulingV1, JSON.toJSONString(imgDO), false);

        GroupMessage groupMessage = JSON.parseObject(message, GroupMessage.class);
//        groupMessage.getMessage()
    }

    @Override
    public void queryAnswer(int group_id, String message) {
        QueryAnswerDO answerDO = new QueryAnswerDO();
        answerDO.setApp_key("8102b22a5e81e840176d9f381ec6f837");
        GroupMessage groupMessage = TulingUtils.getMessage(message);
        String msg = groupMessage.getMessage().toString();
        if (msg.contains("CQ:image")) {
            msg = msg.substring(msg.indexOf("url="), msg.length() - 2);
            System.out.println(msg);
        }
    }
}

package com.qq.message.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qq.message.demo.entry.GroupMessage;
import com.qq.message.demo.entry.User;

public class TulingUtils {

    public static GroupMessage getMessage(String message) {
        JSONObject jsonObject = JSON.parseObject(message);

        GroupMessage groupMessage = new GroupMessage();
        groupMessage.setFont((Integer) jsonObject.get("font"));
        groupMessage.setGroup_id((Integer) jsonObject.get("group_id"));
        groupMessage.setTime((Integer) jsonObject.get("time"));
        groupMessage.setSelf_id((Integer) jsonObject.get("self_id"));
        groupMessage.setPost_type(jsonObject.get("post_type").toString());
        groupMessage.setUser_id(jsonObject.get("user_id"));
        groupMessage.setSub_type(jsonObject.get("sub_type").toString());
        groupMessage.setRaw_message(jsonObject.get("raw_message"));
        groupMessage.setMessage_type(jsonObject.get("message_type").toString());
        groupMessage.setMessage_seq((Integer) jsonObject.get("message_seq"));
        groupMessage.setMessage_id((Integer) jsonObject.get("message_id"));
        groupMessage.setMessage(jsonObject.get("message"));

        User user = new User();
        JSONObject userObj = JSON.parseObject(jsonObject.get("sender").toString());
        user.setUser_id(userObj.get("user_id"));
        user.setTitle(userObj.get("title").toString());
        user.setSex(userObj.get("sex").toString());
        user.setRole(userObj.get("role").toString());
        user.setNickname(userObj.get("nickname").toString());
        user.setLevel(userObj.get("level").toString());
        user.setCard(userObj.get("card").toString());
        user.setArea(userObj.get("area").toString());
        user.setAge((Integer) userObj.get("age"));

        groupMessage.setSender(user);

        return groupMessage;
    }
}

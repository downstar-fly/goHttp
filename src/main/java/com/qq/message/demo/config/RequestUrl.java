package com.qq.message.demo.config;

import com.qq.message.demo.DO.ApiParams;

public enum RequestUrl {
    SendMessageToUser {
        @Override
        public String getUri(ApiParams apiParams) {
            return RequestConfig.address + "/send_private_msg?user_id=" + apiParams.getUser_id() + "&group_id=" + apiParams.getGroup_id() + "&message=" + apiParams.getMessage() + "&auto_escape=" + apiParams.isAuto_escape();
        }
    },

    RecallMessage {
        @Override
        public String getUri(ApiParams apiParams) {
            return RequestConfig.address + "/delete_msg?message_id=" + apiParams.getMessage_id();
        }
    };




    public abstract String getUri(ApiParams apiParams);
}

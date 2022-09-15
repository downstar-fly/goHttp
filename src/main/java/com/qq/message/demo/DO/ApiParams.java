package com.qq.message.demo.DO;

public class ApiParams {
    String user_id;
    String group_id;
    String message;
    boolean auto_escape;
    int message_id;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAuto_escape() {
        return auto_escape;
    }

    public void setAuto_escape(boolean auto_escape) {
        this.auto_escape = auto_escape;
    }
}

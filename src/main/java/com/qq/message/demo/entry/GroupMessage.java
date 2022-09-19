package com.qq.message.demo.entry;

public class GroupMessage extends BaseInfo {
    private String message_type;

    private String sub_type;

    private int message_id;

    private Object user_id;

    private Object message;

    private Object raw_message;

    private int font;

    private User sender;

    private int group_id;

    private Object anonymous;

    private int message_seq;

    public int getMessage_seq() {
        return message_seq;
    }

    public void setMessage_seq(int message_seq) {
        this.message_seq = message_seq;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public Object getUser_id() {
        return user_id;
    }

    public void setUser_id(Object user_id) {
        this.user_id = user_id;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getRaw_message() {
        return raw_message;
    }

    public void setRaw_message(Object raw_message) {
        this.raw_message = raw_message;
    }

    public int getFont() {
        return font;
    }

    public void setFont(int font) {
        this.font = font;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public Object getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Object anonymous) {
        this.anonymous = anonymous;
    }
}

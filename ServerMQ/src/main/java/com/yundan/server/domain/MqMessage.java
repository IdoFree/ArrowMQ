package com.yundan.server.domain;

public class MqMessage {
    private String channel;
    private String message;

    public MqMessage() {
    }

    public MqMessage(String channel, String message) {
        this.channel = channel;
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "MqMessage{" +
                "channel='" + channel + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

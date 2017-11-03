package com.yundan.arrowmq.domain;

public class TransportData {
    private String channel;
    private Object data;

    public TransportData() {
    }

    public TransportData(String channel, Object data) {
        this.channel = channel;
        this.data = data;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

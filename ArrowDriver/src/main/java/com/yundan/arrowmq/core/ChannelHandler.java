package com.yundan.arrowmq.core;

import com.yundan.arrowmq.domain.Message;

public abstract  class ChannelHandler<T> {
    private String channel;
    private T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    abstract public Message pull(String channel);

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }


}

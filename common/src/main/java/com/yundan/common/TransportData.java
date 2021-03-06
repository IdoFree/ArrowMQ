package com.yundan.common;

public class TransportData implements TransportPotocol {
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



    @Override
    public String getTransportData() {
        return this.channel+":"+ this.data.toString()+"\r\n";
    }
}

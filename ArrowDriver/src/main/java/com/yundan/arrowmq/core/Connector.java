package com.yundan.arrowmq.core;

public interface Connector {
    public void connect(String ip, int port);

    public void close();
}

package com.yundan.server;

public class MessageQueueFactory {
    private static ArrowMq arrowMq = new ArrowMq();

    public static ArrowMq getArrowMq(){
        return arrowMq;
    }
}

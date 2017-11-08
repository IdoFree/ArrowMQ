package com.yundan.server;

import com.yundan.server.domain.MqMessage;
import com.yundan.server.exception.NoSuchPileLineException;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ArrowMq {

    private ConcurrentHashMap<String,Queue<Object>> msgPipeline;

     ArrowMq(){
        msgPipeline = new ConcurrentHashMap<>();
    }


    public void putMsg(MqMessage mqMessage){
        this.putMsg(mqMessage.getChannel(),mqMessage.getMessage());
    }

    public void putMsg(String pipelineName, Object msg){
        if(pipelineName == null){
            throw new IllegalArgumentException("Pipe line name can not be null");
        }
        if(msgPipeline.get(pipelineName) == null){
            Queue<Object> queue = new ConcurrentLinkedDeque<>();
            queue.add(msg);
            msgPipeline.put(pipelineName,queue);
        }else{
            msgPipeline.get(pipelineName).add(msg);
        }
    }


    public Object getMsg(String pipelineName) throws NoSuchPileLineException {
        if(pipelineName == null){
            throw new IllegalArgumentException("Pipe line name can not be null");
        }

        if(msgPipeline.get(pipelineName) == null){
            throw new NoSuchPileLineException();
        }

        if(msgPipeline.get(pipelineName).peek()==null){
            return null;
        }

        return msgPipeline.get(pipelineName).poll();
    }
}

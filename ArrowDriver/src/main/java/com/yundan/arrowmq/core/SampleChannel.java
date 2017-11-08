package com.yundan.arrowmq.core;

import com.yundan.common.TransportData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;

import java.nio.charset.Charset;

public class SampleChannel extends Channel {
    public SampleChannel(ChannelFuture f) {
        super(f);
    }

    @Override
    public void write(String channel, String message) {
//        ByteBuf buf  = Unpooled.copiedBuffer(new TransportData(channel,message).getTransportData(), Charset.defaultCharset());
//        this.f.channel().writeAndFlush(buf);
        System.out.println(this.getF().channel().toString());
        MessageRepository.putMessage(this.getF().channel().toString(),new TransportData(channel,message).getTransportData());
    }

    public ChannelFuture getF(){
        return this.f;
    }

    @Override
    public String read(String channel) {
        return MessageRepository.getMessage(channel);
    }
}

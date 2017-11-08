package com.yundan.arrowmq.core;

import io.netty.channel.ChannelFuture;

public abstract class Channel {
    protected ChannelFuture f;

    public Channel(ChannelFuture f) {
        this.f = f;
    }



    abstract public void write(String channel, String message);
    abstract public String  read(String channel);
     public void close(){
        this.f.channel().close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Channel channel = (Channel) o;

        return f != null ? f.equals(channel.f) : channel.f == null;
    }

    @Override
    public int hashCode() {
        return f != null ? f.hashCode() : 0;
    }
}

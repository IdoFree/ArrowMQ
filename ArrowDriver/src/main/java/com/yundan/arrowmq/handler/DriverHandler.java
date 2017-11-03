package com.yundan.arrowmq.handler;

import com.yundan.arrowmq.core.ChannelHandler;
import com.yundan.arrowmq.domain.TransportData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.MessageToByteEncoder;


public class DriverHandler extends MessageToByteEncoder<TransportData> {
    private ChannelHandler channelHandler;

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.alloc().buffer(10);
        TransportData data = new TransportData(channelHandler.getChannel(),channelHandler.getData());
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TransportData transportData, ByteBuf byteBuf) throws Exception {
        int len = transportData.getChannel().length();
    }
}

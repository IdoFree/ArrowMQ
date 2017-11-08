package com.yundan.arrowmq.handler;

import com.yundan.arrowmq.core.Channel;
import com.yundan.arrowmq.core.ChannelHandler;
import com.yundan.arrowmq.core.MessageRepository;
import com.yundan.common.TransportData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;


public class DriverHandler extends ChannelInboundHandlerAdapter {
    private ChannelHandler channelHandler;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().toString());
//        String msg = "test:data\r\n";
//        ByteBuf buf = ctx.alloc().buffer(msg.length());
//        buf.writeBytes(msg.getBytes());
//        System.out.println("active");
//        ctx.channel().writeAndFlush(buf);
        ;
        System.out.println(ctx.channel().toString());
        ByteBuf buf  = Unpooled.copiedBuffer(MessageRepository.getMessage(ctx.channel().toString()), Charset.defaultCharset());
        ctx.channel().writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object linebuf) throws Exception {
        String line = ((ByteBuf) linebuf).toString(Charset.defaultCharset());
        MessageRepository.putMessage(ctx.channel(),line);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }



}

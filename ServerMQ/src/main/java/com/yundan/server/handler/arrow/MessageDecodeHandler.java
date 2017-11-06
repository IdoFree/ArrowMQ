package com.yundan.server.handler.arrow;

import com.yundan.server.MessageQueueFactory;
import com.yundan.server.domain.MqMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.nio.charset.Charset;

public class MessageDecodeHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object lineBuf) throws Exception {
//        byte[] line = new byte[((ByteBuf) lineBuf).readableBytes()];

        String line = ((ByteBuf) lineBuf).toString(Charset.defaultCharset());
        line = line.trim();
        if (line.length() == 0) {
            ((ByteBuf) lineBuf).release();
            ctx.channel().close();
        } else {
            System.out.println("Got msg: " + line);
            String[] m = line.split(":");
            if(m.length ==1){
                String msg = (String) MessageQueueFactory.getArrowMq().getMsg(m[0]);
                ByteBuf bf = ctx.alloc().buffer(128);
                bf.writeBytes(msg.getBytes());
                ctx.writeAndFlush(bf);
            }else{

                MessageQueueFactory.getArrowMq().putMsg(m[0],m[1]);
            }

            // reuse the ByteBuf, no need to release as it is released on writing to wire
//            ctx.write(lineBuf);
            // force write
//            ctx.flush();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

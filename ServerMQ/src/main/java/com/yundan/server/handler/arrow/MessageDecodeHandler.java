package com.yundan.server.handler.arrow;

import com.yundan.common.Response;
import com.yundan.common.TransportData;
import com.yundan.server.MessageQueueFactory;
import com.yundan.server.exception.NoSuchPileLineException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.haproxy.HAProxyProxiedProtocol;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.Slf4JLoggerFactory;

import java.nio.charset.Charset;
import java.util.logging.Logger;

public class MessageDecodeHandler extends ChannelInboundHandlerAdapter {
    InternalLogger  logger = Slf4JLoggerFactory.getInstance(MessageDecodeHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object lineBuf)  {

        String line = ((ByteBuf) lineBuf).toString(Charset.defaultCharset());
        line = line.trim();
        if (line.length() == 0) {
            ((ByteBuf) lineBuf).release();
            ctx.channel().close();
        } else {
            System.out.println("Got msg: " + line);
            String[] m = line.split(":");
            //only specify the channel name , mean to get the message in the pipe line
                if(m.length ==1){
                    String msg = null;
                    try {
                        msg = (String) MessageQueueFactory.getArrowMq().getMsg(m[0]);
                    } catch (NoSuchPileLineException e) {
                        logger.error("no such pipe line "+m[0]);
                        ((ByteBuf) lineBuf).release();
                        ctx.channel().close();

                    }
                    if(msg == null){
                        Response rsp = new Response("no message available");
                        ByteBuf bf = ctx.alloc().buffer(rsp.getTransportData().length());
                        bf.writeBytes(rsp.getTransportData().getBytes());
                        ctx.writeAndFlush(bf);
                        return ;
                    }
                    Response rsp = new Response(msg);

                    ByteBuf bf = ctx.alloc().buffer(rsp.getTransportData().length());
                    bf.writeBytes(msg.getBytes());
                    ctx.writeAndFlush(bf);
                }else{

                    MessageQueueFactory.getArrowMq().putMsg(m[0],m[1]);
                }




        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

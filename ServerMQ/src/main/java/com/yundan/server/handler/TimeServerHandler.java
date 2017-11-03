package com.yundan.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.time.Instant;
import java.util.List;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)



        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeBytes(Instant.now().toString().getBytes());
        System.out.println("writing the unix time");
//            final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        ctx.writeAndFlush(time);
//        f.addListener(ChannelFutureListener.CLOSE); // (4)
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    public static class FlashHandler extends ByteToMessageDecoder{

        @Override
        protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        }
    }

    public static class OutHandler extends ChannelOutboundHandlerAdapter{

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("message is going out");
            System.out.println("close channel");
        }
    }

}


